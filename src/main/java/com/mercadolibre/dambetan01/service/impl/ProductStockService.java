package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.mapper.ProductStockPurchaseOrderMapper;
import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;
import com.mercadolibre.dambetan01.repository.ProductStockRepository;
import com.mercadolibre.dambetan01.service.IProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductStockService implements IProductStockService {
    private final ProductStockRepository productStockRepository;

    @Override
    public List<ProductStock> findByProductIdAndCurrentQuantityGreaterThanEqual(Long productId, Integer currentQuantity) {
        return productStockRepository.findByProductIdAndCurrentQuantityGreaterThanEqual(productId, currentQuantity);
    }

    @Override
    public ProductStock save(ProductStock productStock) {
        return productStockRepository.save(productStock);
    }

    // Essa isolacao garante um lock nas linhas do select
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<ProductStockPurchaseOrder> decrementByProduct(Integer valueToDecrement, Long productId, PurchaseOrder purchaseOrder) {
        List<ProductStock> productStocks = productStockRepository.findByProductId(productId);

        if(!hasEnoughProduct(productStocks, valueToDecrement)) return null;

        Integer remainValueToDecrement = valueToDecrement;
        List<ProductStockPurchaseOrder> productStocksUsed = new ArrayList<>();

        for(ProductStock productStock : productStocks) {
            Integer decrementedValue;

            if(productStock.getCurrentQuantity() >= remainValueToDecrement) {
                decrementedValue = remainValueToDecrement;
                remainValueToDecrement = 0;
            } else {
                remainValueToDecrement -= productStock.getCurrentQuantity();
                decrementedValue = productStock.getCurrentQuantity();
            }

            decrementFromProductStock(decrementedValue, productStock);

            productStocksUsed.add(ProductStockPurchaseOrderMapper.buildFrom(productStock, decrementedValue, purchaseOrder));
            if(remainValueToDecrement == 0) return productStocksUsed;
        }

        return null;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ProductStock incrementStock(Integer valueToIncrement, Long stockId) {
        ProductStock productStock = productStockRepository.findById(stockId).orElseThrow(() -> new NotFoundException("not found sotck with id " + stockId));
        productStock.setCurrentQuantity(productStock.getCurrentQuantity() + valueToIncrement);
        return productStockRepository.save(productStock);
    }

    private boolean hasEnoughProduct(List<ProductStock> productStocks, Integer minQuantity) {
        if(productStocks == null || productStocks.size() == 0) return false;
        Integer currentQuantity = 0;
        for(ProductStock productStock : productStocks) {
            currentQuantity += productStock.getCurrentQuantity();
            if(currentQuantity >= minQuantity) return true;
        }
        return false;
    }

    @Override
    public ProductStock decrementFromProductStock(Integer valueToDecrement, ProductStock productStock) {
        Integer newStockQuantity = productStock.getCurrentQuantity() - valueToDecrement;
        if(newStockQuantity < 0) throw new RuntimeException("Nao ha quantidade suficiente do produto");
        productStock.setCurrentQuantity(newStockQuantity);
        return productStockRepository.save(productStock);
    }
}

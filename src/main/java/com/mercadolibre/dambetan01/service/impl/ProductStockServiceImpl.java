package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.ProductDueDateDTO;
import com.mercadolibre.dambetan01.exceptions.IllegalCategoryProductSector;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.*;
import com.mercadolibre.dambetan01.repository.ProductStockRepository;
import com.mercadolibre.dambetan01.service.ProductStockService;
import com.mercadolibre.dambetan01.service.RepresentantService;
import com.mercadolibre.dambetan01.service.SectorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductStockServiceImpl implements ProductStockService {
    private final ProductStockRepository repository;
    private final SectorService sectorService;
    private final RepresentantService representantService;

    public ProductStockServiceImpl(ProductStockRepository repository, SectorServiceImpl sectorService, RepresentantService representantService) {
        this.repository = repository;
        this.sectorService = sectorService;
        this.representantService = representantService;
    }

    @Override
    public List<ProductStock> saveAll(List<ProductStock> productStock) {
        return repository.saveAll(productStock);
    }

    @Override
    public void checkCategoryProductAndSector(List<ProductStock> productStocks, Category category) {
        productStocks.stream()
                .map(ProductStock::getProduct)
                .filter(product -> !product.getCategory().getName().equalsIgnoreCase(category.getName()))
                .findFirst()
                .ifPresent(product -> {
                    throw new IllegalCategoryProductSector("Product " + product.getName() + " has category diff of Sector");
                });
    }

    public Integer getQuantityProductsByProductStocks(List<ProductStock> productStocks) {
        Optional<Integer> quantity = productStocks.stream()
                .map(ProductStock::getInitialQuantity)
                .filter(Objects::nonNull)
                .reduce(Integer::sum);

        if (quantity.isEmpty()) {
            quantity = productStocks.stream()
                    .map(ProductStock::getCurrentQuantity)
                    .filter(Objects::nonNull)
                    .reduce(Integer::sum);
        }
        return quantity.get();
    }

    public List<ProductStock> addOrderOnProductStock(Order order) {
        List<ProductStock> productStocks = order.getProductStocks();
        productStocks.forEach(productStock -> productStock.setCurrentQuantity(productStock.getInitialQuantity()));
        productStocks.forEach(p -> p.setOrder(order));
        return productStocks;
    }

    @Override
    public List<ProductDueDateDTO> findAllProductStockDueDateBySector(Integer daysFuture, Long idSector, Long idRepresentant) {

        LocalDate today = LocalDate.now();
        LocalDate dateDaysFuture = today.plusDays(daysFuture);

        Sector sector = this.sectorService.findById(idSector);
        this.representantService.checkRelationBetweenRepresentantAndWarehouse(idRepresentant, sector.getWarehouse());

        List<ProductDueDateDTO> productStockList = repository.findAllProductStockDueDate(dateDaysFuture, idSector);

        if (productStockList.size() == 0) throw new NotFoundException("Not found products in sector");

        return productStockList;
    }

    @Override
    public List<ProductDueDateDTO> findAllProductStockDueDateFilters(Integer daysFuture,
                                                                     Long idRepresentant, String category,
                                                                     String sorted) {
        String nameCategory = "";

        if(category.equalsIgnoreCase("FR")){
            nameCategory = "fresco";
        } else if (category.equalsIgnoreCase("RF")){
            nameCategory = "refrigerado";
        } else {
            nameCategory = "congelado";
        }

        LocalDate today = LocalDate.now();
        LocalDate dateDaysFuture = today.plusDays(daysFuture);

        List<ProductDueDateDTO> productStockList = repository
                .findAllProductStockDueDateCategory(dateDaysFuture, nameCategory, idRepresentant);

        if (sorted.equals("asc")){
            productStockList.sort(Comparator.comparing(ProductDueDateDTO::getDueDate));
        }

        if (productStockList.size() == 0) throw new NotFoundException("Not found products in warehouse");

        return productStockList;
    }

}

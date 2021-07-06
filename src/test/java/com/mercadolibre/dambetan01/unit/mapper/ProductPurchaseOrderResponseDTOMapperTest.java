package com.mercadolibre.dambetan01.unit.mapper;

import com.mercadolibre.dambetan01.dtos.response.purchase.ProductPurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.mapper.ProductPurchaseOrderResponseDTOMapper;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductPurchaseOrderResponseDTOMapperTest {
    private ProductPurchaseOrderResponseDTOMapper mapper;

    @BeforeEach
    public void setUp(){
        mapper = new ProductPurchaseOrderResponseDTOMapper();
    }

    @Test
    public void shouldConvertModelToDtoCorrectly(){
        ProductStockPurchaseOrder productStockPurchaseOrder = new ProductStockPurchaseOrder();
        Product p = new Product();
        p.setId(1L);
        productStockPurchaseOrder.setQuantity(12);
        productStockPurchaseOrder.setProduct(p);
        ProductPurchaseOrderResponseDTO dto = mapper.modelToDto(productStockPurchaseOrder);
        Assertions.assertEquals(productStockPurchaseOrder.getProduct().getId(), dto.getProductId());
        Assertions.assertEquals(productStockPurchaseOrder.getQuantity(), dto.getQuantity());
    }
}

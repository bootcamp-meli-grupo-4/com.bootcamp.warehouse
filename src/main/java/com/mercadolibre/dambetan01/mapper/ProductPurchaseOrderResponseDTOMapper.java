package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.response.purchase.ProductPurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import org.springframework.stereotype.Component;

@Component
public class ProductPurchaseOrderResponseDTOMapper implements
        MapperModelToDto<ProductStockPurchaseOrder, ProductPurchaseOrderResponseDTO>{
    @Override
    public ProductPurchaseOrderResponseDTO modelToDto(ProductStockPurchaseOrder model) {
        ProductPurchaseOrderResponseDTO dto = new ProductPurchaseOrderResponseDTO();
        dto.setProductId(model.getProduct().getId());
        dto.setQuantity(model.getQuantity());
        return dto;
    }
}

package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.response.purchase.GetPurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.purchase.ProductPurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPurchaseOrderResponseDTOMapper implements MapperModelToDto <PurchaseOrder, GetPurchaseOrderResponseDTO>{

    private final ProductPurchaseOrderResponseDTOMapper productMapper;
    private final OrderStatusResponseDTOMapper statusResponseDTOMapper;


    @Override
    public GetPurchaseOrderResponseDTO modelToDto(PurchaseOrder model) {
        GetPurchaseOrderResponseDTO dto = new GetPurchaseOrderResponseDTO();
        dto.setDate(model.getDate());
        dto.setStatus(this.statusResponseDTOMapper.modelToDto(model.getStatusPurchaseOrder()));
        List<ProductPurchaseOrderResponseDTO> productList = new ArrayList<>();
        model.getProducts().forEach(p -> productList.add(productMapper.modelToDto(p)));
        dto.setProducts(productList);
        return dto;
    }
}

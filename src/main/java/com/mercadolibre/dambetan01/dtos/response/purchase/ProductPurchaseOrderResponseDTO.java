package com.mercadolibre.dambetan01.dtos.response.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseOrderResponseDTO {
    @JsonProperty("productId")
    private Long productId;

    private Integer quantity;
}

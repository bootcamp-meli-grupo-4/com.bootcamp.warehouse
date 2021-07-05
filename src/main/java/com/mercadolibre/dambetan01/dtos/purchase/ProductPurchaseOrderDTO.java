package com.mercadolibre.dambetan01.dtos.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseOrderDTO {
    @Min(value = 1, message = "id do produto deve ser maior que 0")
    @JsonProperty("productId")
    private Long productId;

    @Min(value = 1, message = "a quantidade do produto deve ser maior que 0")
    private Integer quantity;
}

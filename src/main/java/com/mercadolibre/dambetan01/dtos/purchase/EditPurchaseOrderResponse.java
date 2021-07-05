package com.mercadolibre.dambetan01.dtos.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPurchaseOrderResponse {
    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;
}

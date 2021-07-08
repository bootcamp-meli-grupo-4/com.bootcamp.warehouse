package com.mercadolibre.dambetan01.dtos.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDTO {
    @JsonProperty("statusCode")
    private String statusCode;
}

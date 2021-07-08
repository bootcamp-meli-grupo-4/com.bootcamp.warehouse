package com.mercadolibre.dambetan01.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUnavailableResponseDTO {
    @JsonProperty("productId")
    private Long productId;
}

package com.mercadolibre.dambetan01.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorDto {
    @JsonProperty("sectionCode")
    private Long sectionCode;
    @JsonProperty("warehouseCode")
    private Long warehouseCode;
}

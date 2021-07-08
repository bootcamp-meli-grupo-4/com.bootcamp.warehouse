package com.mercadolibre.dambetan01.dtos.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class SectorDto {
    @JsonProperty("sectionCode")
    @NotNull(message = "section is required")
    @Range(min = 1, message = "sectionCode must start at 1")
    private Long sectionCode;

    @JsonProperty("warehouseCode")
    @NotNull(message = "section is required")
    @Range(min = 1, message = "warehouseCode must start at 1")
    private Long warehouseCode;
}

package com.mercadolibre.dambetan01.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class SectorResponseDTO {
    @JsonProperty("sectionCode")
    private Long sectionCode;

    @JsonProperty("warehouseCode")
    private Long warehouseCode;

}

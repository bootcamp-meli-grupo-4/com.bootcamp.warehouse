package com.mercadolibre.dambetan01.dtos.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Builder
public class PostProductStockDto {

    @JsonProperty("currentTemperature")
    @NotNull(message = "currentTemperature is required")
    private Double currentTemperature;

    @JsonProperty("minimumTemperature")
    @NotNull(message = "minimumTemperature is required")
    private Double minimumTemperature;

    @JsonProperty("initialQuantity")
    @NotNull(message = "initialQuantity is required")
    @Range(min = 1, message = "initialQuantity must start at 1")
    private Integer initialQuantity;

    @JsonProperty("manufacturingDate")
    @NotNull(message = "manufacturingDate is required")
    private LocalDate manufacturingDate;

    @JsonProperty("manufacturingTime")
    @NotNull(message = "manufacturingTime is required")
    private LocalDateTime manufacturingTime;

    @JsonProperty("dueDate")
    @NotNull(message = "dueDate is required")
    private LocalDate dueDate;

    @JsonProperty("productId")
    @NotNull(message = "productId is required")
    @Range(min = 1, message = "productId must start at 1")
    private Long productId;

}

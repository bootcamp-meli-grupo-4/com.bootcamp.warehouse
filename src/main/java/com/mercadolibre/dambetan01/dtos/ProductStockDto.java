package com.mercadolibre.dambetan01.dtos;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ProductStockDto {

    @JsonProperty("batchNumber")
    private Long batchNumber;

    @JsonProperty("currentTemperature")
    @NotNull(message = "currentTemperature is required")
    private Double currentTemperature;

    @JsonProperty("minimumTemperature")
    @NotNull(message = "minimumTemperature is required")
    private Double minimumTemperature;

    @JsonProperty("initialQuantity")
    private Integer initialQuantity;

    @JsonProperty("currentQuantity")
    private Integer currentQuantity;

    @JsonProperty("manufacturingDate")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @NotNull(message = "manufacturingDate is required")
    private LocalDate manufacturingDate;

    @JsonProperty("manufacturingTime")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @NotNull(message = "manufacturingTime is required")
    private LocalDateTime manufacturingTime;

    @JsonProperty("dueDate")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @NotNull(message = "dueDate is required")
    private LocalDate dueDate;

    @JsonProperty("productId")
    @NotNull(message = "productId is required")
    @Range(min = 1, message = "productId must start at 1")
    private Long productId;

}

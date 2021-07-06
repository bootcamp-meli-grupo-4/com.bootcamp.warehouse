package com.mercadolibre.dambetan01.dtos.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProductStockResponseDto {

    @JsonProperty("batchNumber")
    private Long batchNumber;

    @JsonProperty("currentTemperature")
    private Double currentTemperature;

    @JsonProperty("minimumTemperature")
    private Double minimumTemperature;

    @JsonProperty("initialQuantity")
    private Integer initialQuantity;

    @JsonProperty("currentQuantity")
    private Integer currentQuantity;

    @JsonProperty("manufacturingDate")
    private LocalDate manufacturingDate;

    @JsonProperty("manufacturingTime")
    private LocalDateTime manufacturingTime;

    @JsonProperty("dueDate")
    private LocalDate dueDate;

    @JsonProperty("productId")
    private Long productId;

}

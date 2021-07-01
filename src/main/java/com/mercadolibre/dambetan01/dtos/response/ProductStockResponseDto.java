package com.mercadolibre.dambetan01.dtos.response;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProductStockResponseDto {
    private Double currentTemperature;

    private Double minimumTemperature;

    private Integer initialQuantity;

    private Integer currentQuantity;

    private LocalDate manufacturingDate;

    private LocalDateTime manufacturingTime;

    private LocalDate dueDate;

    private Long productId;

}

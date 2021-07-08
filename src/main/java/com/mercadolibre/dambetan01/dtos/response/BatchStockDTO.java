package com.mercadolibre.dambetan01.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockDTO {
    private Long batchNumber;
    private Integer currentQuantity;
    private LocalDate dueDate;

}

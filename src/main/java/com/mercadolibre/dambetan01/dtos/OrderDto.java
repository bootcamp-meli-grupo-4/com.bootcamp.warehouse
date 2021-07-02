package com.mercadolibre.dambetan01.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @JsonProperty("orderNumber")
    private Long orderNumber;

    @JsonProperty("orderDate")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate orderDate;

    @JsonProperty("section")
    private SectorDto section;

    @JsonProperty("batchStock")
    private List<ProductStockDto> batchStock;



}

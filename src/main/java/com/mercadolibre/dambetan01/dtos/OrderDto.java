package com.mercadolibre.dambetan01.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @JsonProperty("orderDate")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Calendar orderDate;

    @JsonProperty("section")
    private SectorDto section;

    @JsonProperty("batchStock")
    private List<ProductStockDto> batchStock;



}

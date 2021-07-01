package com.mercadolibre.dambetan01.dtos;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {

    private LocalDate orderDate;

    private SectorDto sector;

    private List<ProductStockDto> productStocks;
}

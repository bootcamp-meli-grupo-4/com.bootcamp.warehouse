package com.mercadolibre.dambetan01.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate orderDate;

    @JsonProperty("section")
    private SectorDto sector;

    @JsonProperty("batchStock")
    private List<ProductStockDto> productStocks;


    public OrderDto() {
    }

    public OrderDto(LocalDate orderDate, SectorDto sector, List<ProductStockDto> productStocks) {
        this.orderDate = orderDate;
        this.sector = sector;
        this.productStocks = productStocks;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public SectorDto getSector() {
        return sector;
    }

    public void setSector(SectorDto sector) {
        this.sector = sector;
    }

    public List<ProductStockDto> getProductStocks() {
        return productStocks;
    }

    public void setProductStocks(List<ProductStockDto> productStocks) {
        this.productStocks = productStocks;
    }
}

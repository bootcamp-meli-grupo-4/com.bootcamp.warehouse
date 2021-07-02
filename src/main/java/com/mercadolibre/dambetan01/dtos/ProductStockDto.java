package com.mercadolibre.dambetan01.dtos;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockDto {

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
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Calendar manufacturingDate;

    @JsonProperty("manufacturingTime")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Calendar manufacturingTime;

    @JsonProperty("dueDate")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Calendar dueDate;

    @JsonProperty("productId")
    private Long productId;

}

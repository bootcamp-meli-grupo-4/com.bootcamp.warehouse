package com.mercadolibre.dambetan01.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDate;

@Value
public class ProductListDTO {

    @JsonProperty("id")
    Long id;

    @JsonProperty("name")
    String name;

    @JsonProperty("category")
    String category;

    @JsonProperty("dueDate")
    LocalDate dueDate;

}

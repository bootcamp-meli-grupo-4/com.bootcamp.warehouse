package com.mercadolibre.dambetan01.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

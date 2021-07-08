package com.mercadolibre.dambetan01.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductByWarehouseDTO {
    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("warehouses")
    private List<TotalProductByWareHouseDTO> warehouses;
}

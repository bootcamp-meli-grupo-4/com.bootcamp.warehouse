package com.mercadolibre.dambetan01.dtos.response;

import com.mercadolibre.dambetan01.dtos.inbound.SectorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockSearchDTO {
    private SectorDto sector;
    private Long productId;
    private List<BatchStockDTO> batchStock;

}

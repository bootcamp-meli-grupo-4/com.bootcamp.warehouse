package com.mercadolibre.dambetan01.dtos.response.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPurchaseOrderResponseDTO {
    private LocalDate date;
    private OrderStatusResponseDTO status;
    private List<ProductPurchaseOrderResponseDTO> products;
    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

}

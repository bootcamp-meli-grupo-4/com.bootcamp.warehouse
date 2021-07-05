package com.mercadolibre.dambetan01.dtos.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPurchaseOrderDTO {
    @Valid
    @NotNull(message = "o campo products e obrigatorio")
    @NotEmpty(message = "o campo products e obrigatorio")
    private List<ProductPurchaseOrderDTO> products;
}

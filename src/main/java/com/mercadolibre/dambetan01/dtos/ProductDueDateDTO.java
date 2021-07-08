package com.mercadolibre.dambetan01.dtos;


import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDueDateDTO {

    private Long batchNumber;
    private Long productId;
    private Long productTypeId;
    private LocalDate dueDate;
    private Integer quantity;
}

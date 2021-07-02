package com.mercadolibre.dambetan01.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Buyer extends Account{
    @OneToOne(mappedBy = "buyer")
    private PurchaseOrder purchaseOrder;
}

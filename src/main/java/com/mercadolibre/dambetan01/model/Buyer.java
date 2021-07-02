package com.mercadolibre.dambetan01.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Buyer extends Account{
    @OneToOne(mappedBy = "purchase_order")
    private PurchaseOrder purchaseOrder;
}

package com.mercadolibre.dambetan01.model;

import javax.persistence.OneToOne;

public class Buyer extends Account{
    @OneToOne(mappedBy = "purchase_order")
    private PurchaseOrder purchaseOrder;
}

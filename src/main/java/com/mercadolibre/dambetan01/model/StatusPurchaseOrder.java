package com.mercadolibre.dambetan01.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class StatusPurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "purchase_order")
    private List<PurchaseOrder> purchaseOrder;

}

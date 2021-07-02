package com.mercadolibre.dambetan01.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusPurchaseOrder statusPurchaseOrder;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<ProductOrder> products;

}

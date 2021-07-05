package com.mercadolibre.dambetan01.model.purchase;

import com.mercadolibre.dambetan01.model.user.Buyer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private List<ProductStockPurchaseOrder> products;

}

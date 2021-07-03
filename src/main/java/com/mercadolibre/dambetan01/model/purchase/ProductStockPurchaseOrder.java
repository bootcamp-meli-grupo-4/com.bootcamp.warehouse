package com.mercadolibre.dambetan01.model.purchase;


import com.mercadolibre.dambetan01.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockPurchaseOrder {

    @EmbeddedId
    private ProductStockPurchaseOrderKey productStockPurchaseOrderKey = new ProductStockPurchaseOrderKey();

    @ManyToOne
    @MapsId("purchaseOrderId")
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private BigDecimal bill;

    @ManyToOne
    @MapsId("productStockId")
    @JoinColumn(name = "product_stock_id")
    private ProductStock productStock;
}

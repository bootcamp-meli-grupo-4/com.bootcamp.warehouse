package com.mercadolibre.dambetan01.model.purchase;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockPurchaseOrderKey implements Serializable {
    @Column(name = "purchase_order_id")
    Long purchaseOrderId;

    @Column(name = "product_stock_id")
    Long productStockId;
}

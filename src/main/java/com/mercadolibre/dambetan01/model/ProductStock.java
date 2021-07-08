package com.mercadolibre.dambetan01.model;

import com.mercadolibre.dambetan01.model.Order;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double currentTemperature;

    private Double minimumTemperature;

    private Integer initialQuantity;

    private Integer currentQuantity;

    private LocalDate manufacturingDate;

    private LocalDateTime manufacturingTime;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToMany(mappedBy = "productStock")
    private List<ProductStockPurchaseOrder> productStockPurchaseOrders;
}

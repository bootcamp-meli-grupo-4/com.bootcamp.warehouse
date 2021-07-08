package com.mercadolibre.dambetan01.model;

import com.mercadolibre.dambetan01.model.user.Seller;
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
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private BigDecimal price;
}

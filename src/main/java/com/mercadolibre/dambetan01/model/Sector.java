package com.mercadolibre.dambetan01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @OneToMany(mappedBy = "sector")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private Long quantityMax;

    @Version
    private Long currentQuantity;
}

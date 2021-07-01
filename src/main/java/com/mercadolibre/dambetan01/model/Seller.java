package com.mercadolibre.dambetan01.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Seller extends Account {
    @OneToMany(mappedBy = "seller")
    private List<Product> products;
}

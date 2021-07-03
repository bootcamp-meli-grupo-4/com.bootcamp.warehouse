package com.mercadolibre.dambetan01.model.user;

import com.mercadolibre.dambetan01.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Seller extends Account {
    @OneToMany(mappedBy = "seller")
    private List<Product> products;
}

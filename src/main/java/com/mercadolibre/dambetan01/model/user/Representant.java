package com.mercadolibre.dambetan01.model.user;

import com.mercadolibre.dambetan01.model.Warehouse;
import com.mercadolibre.dambetan01.model.user.Account;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Representant extends Account {

    @OneToMany(mappedBy = "representant")
    private List<Warehouse> warehouses;
}

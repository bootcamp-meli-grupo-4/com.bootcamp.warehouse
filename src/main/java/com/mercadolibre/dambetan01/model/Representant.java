package com.mercadolibre.dambetan01.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Representant extends Account {

    @OneToMany(mappedBy = "representant")
    private List<Warehouse> warehouses;
}

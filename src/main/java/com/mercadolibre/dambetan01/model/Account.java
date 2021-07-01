package com.mercadolibre.dambetan01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username, password;

    private Integer rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_country_house_fk", nullable = false)
    private CountryHouse countryHouse;
}
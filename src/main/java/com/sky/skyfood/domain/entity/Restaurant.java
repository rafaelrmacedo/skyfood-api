package com.sky.skyfood.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "restaurant")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurant {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "tax_delivery", nullable = false)
    private BigDecimal taxDelivery;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cuisine cuisine;
}

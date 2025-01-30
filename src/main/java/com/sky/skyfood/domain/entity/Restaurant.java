package com.sky.skyfood.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurant_payment_methods", joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id")) // changes the columns name
    private List<PaymentMethod> paymentMethod = new ArrayList<>();
}

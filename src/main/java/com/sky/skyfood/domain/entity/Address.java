package com.sky.skyfood.domain.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable // it means that this class can be embedded in another class as an attribute
public class Address {

    private String cep;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
}

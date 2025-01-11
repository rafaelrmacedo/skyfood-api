package com.sky.skyfood.domain.repository;

import com.sky.skyfood.domain.entity.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {

    List<PaymentMethod> all();
    PaymentMethod getById(Long id);
    PaymentMethod add(PaymentMethod paymentMethod);
    void remove(PaymentMethod paymentMethod);

}

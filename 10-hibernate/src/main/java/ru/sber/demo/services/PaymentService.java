package ru.sber.demo.services;


import ru.sber.demo.entity.Payment;

public interface PaymentService {
    boolean pay(Payment payment);
}

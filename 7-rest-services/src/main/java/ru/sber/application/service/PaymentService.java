package ru.sber.application.service;

import ru.sber.application.entity.Payment;

public interface PaymentService {
    boolean pay(Payment payment);
}

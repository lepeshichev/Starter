package ru.sber.application.services;

import ru.sber.application.entity.Payment;

public interface PaymentService {
    boolean pay(Payment payment);
}

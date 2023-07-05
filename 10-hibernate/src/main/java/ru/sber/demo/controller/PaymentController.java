package ru.sber.demo.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.demo.entity.Payment;
import ru.sber.demo.services.PaymentService;

@Slf4j
@RequestMapping("payment")
@RestController
public class PaymentController {
    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<?> pay(@Valid @RequestBody Payment payment) {
        boolean isPay = paymentService.pay(payment);
        if (isPay) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().body("Средств на счету недостаточно");
        }
    }
}

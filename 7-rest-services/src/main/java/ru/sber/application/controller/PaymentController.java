package ru.sber.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.application.entity.Payment;
import ru.sber.application.service.LocalPaymentService;

@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
    LocalPaymentService localPaymentService;

    public PaymentController(LocalPaymentService localPaymentService) {
        this.localPaymentService = localPaymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> pay(@RequestBody Payment payment) {
        boolean isPay = localPaymentService.pay(payment);
        if (isPay) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

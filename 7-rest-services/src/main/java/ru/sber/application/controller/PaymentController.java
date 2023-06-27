package ru.sber.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.application.entity.Payment;

@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        log.info("Получен платеж " + payment.getAmount());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(payment);
    }
}

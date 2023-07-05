package ru.sber.demo.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {
    @NotNull(message = "Не указан номер карты")
    long cardNumber;
    @NotNull(message = "Не указан id клиента")
    long clientId;
}

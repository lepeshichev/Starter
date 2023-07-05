package ru.sber.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {
    long cardNumber;
    long userId;
}

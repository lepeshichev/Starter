package ru.sber.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class Card {
    private long cardNumber;
    private BigDecimal balance;
}

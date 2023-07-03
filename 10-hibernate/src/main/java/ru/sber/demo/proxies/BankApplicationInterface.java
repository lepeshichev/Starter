package ru.sber.demo.proxies;

import java.math.BigDecimal;

public interface BankApplicationInterface {
    BigDecimal getBalance(long cardNumber);
}

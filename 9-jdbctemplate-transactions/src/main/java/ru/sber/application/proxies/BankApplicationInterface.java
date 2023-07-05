package ru.sber.application.proxies;

import java.math.BigDecimal;

public interface BankApplicationInterface {
    BigDecimal getBalance(long cardNumber);
}

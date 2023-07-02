package ru.sber.application.repository;

import java.math.BigDecimal;

public interface CartRepository {
    boolean addToCart(long idClient, long idProduct, int amount);
    boolean updateAmount(long idClient, long idProduct, int amount);
    boolean deleteProduct(long idClient, long idProduct);
    BigDecimal getCartPrice(long userId);
    boolean clear(long userId);
}

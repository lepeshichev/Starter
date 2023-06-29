package ru.sber.application.repository;

import ru.sber.application.entity.Cart;

import java.util.Optional;

public interface CartRepository {
    Cart createCart();
    Optional<Cart> addToCart(long idCart, long idProduct);
    Optional<Cart> findById(long id);
    Optional<Cart> updateProductAmount(long idCart, long idProduct, int amount);
    boolean deleteProduct(long idCart, long idProduct);
}

package ru.sber.demo.services;

import ru.sber.demo.entity.Product;

import java.util.List;

public interface CartService {
    boolean add(long userId, long productId, int amount);

    boolean updateAmount(long userId, long productId, int amount);

    boolean deleteProduct(long userId, long productId);

    void clearCart(long userId);
    List<Product> getListOfProductsInCart(long userId);
    int countProductsInCart(long userId);
}
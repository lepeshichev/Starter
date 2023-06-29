package ru.sber.application.repository;

public interface CartRepository {
    boolean addToCart(long idClient, long idProduct, int amount);
    boolean updateAmount(long idClient, long idProduct, int amount);
    boolean deleteProduct(long idClient, long idProduct);
}

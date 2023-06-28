package ru.sber.application.repository;

import ru.sber.application.entity.Product;

public interface BasketRepository {
    boolean add(Product product);
    boolean update(int amount, long id);
    boolean deleteById(long id);
}

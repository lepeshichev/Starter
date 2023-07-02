package ru.sber.application.repository;

import ru.sber.application.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    long save(Product product);
    boolean update(Product product);
    boolean deleteById(long id);
    Optional<Product> findById(long id);
    List<Product> findAll(String name);
    void update(long userId);
}

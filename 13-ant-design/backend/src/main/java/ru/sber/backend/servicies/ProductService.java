package ru.sber.backend.servicies;

import ru.sber.backend.entity.Product;

import java.util.List;

public interface ProductService {
    long save(Product product);
    List<Product> findAll(String name);
    boolean deleteById(long id);
}

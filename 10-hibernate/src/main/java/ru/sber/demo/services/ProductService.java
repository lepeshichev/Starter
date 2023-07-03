package ru.sber.demo.services;

import ru.sber.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    long addNewProduct(Product product);

    Optional<Product> findById(long id);

    List<Product> findAllByName(String name);

    boolean update(Product product);

    boolean checkProductExistence(long productId);

    boolean deleteById(long productId);
}

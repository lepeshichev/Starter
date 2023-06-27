package ru.sber.application.repository;

import org.springframework.stereotype.Repository;
import ru.sber.application.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class LocalProductRepository implements ProductRepository {

    private List<Product> products = new ArrayList<>(List.of(
            new Product(1, "Креветки", BigDecimal.valueOf(385), 100),
            new Product(1, "Кальмары", BigDecimal.valueOf(370), 5),
            new Product(1, "Мидии", BigDecimal.valueOf(500), 50),
            new Product(1, "Окунь", BigDecimal.valueOf(260), 10),
            new Product(1, "Тунец", BigDecimal.valueOf(800), 5),
            new Product(1, "Горбуша", BigDecimal.valueOf(170), 15)
    ));

    @Override
    public long save(Product product) {
        long id = new Random().nextLong(999) + 1;
        product.setId(id);
        products.add(product);
        return id;
    }

    @Override
    public boolean update(Product product) {
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setAmount(product.getAmount());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return products.removeIf(product -> product.getId() == id);
    }

    @Override
    public Optional<Product> findById(long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findAny();
    }

    @Override
    public List<Product> findAll(String name) {
        if (name == null) {
            return products;
        }
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .toList();
    }
}

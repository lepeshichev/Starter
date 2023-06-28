package ru.sber.application.repository;

import org.springframework.stereotype.Repository;
import ru.sber.application.entity.Basket;
import ru.sber.application.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LocalBasketRepository implements BasketRepository {

    private List<Basket> baskets = new ArrayList<>(List.of(
            new Basket(1, new ArrayList<>(), 123)
    ));

    @Override
    public boolean add(Product product) {
        return baskets.get(1).getProducts().add(product);
    }

    @Override
    public boolean update(int amount, long id) {
        List<Product> products = new ArrayList<>(baskets.get(1).getProducts());
        for(Product product : products) {
            if (product.getId() == id) {
                product.setAmount(amount);
                baskets.get(1).setProducts(products);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        List<Product> products = new ArrayList<>(baskets.get(1).getProducts());
        int size = products.size();
        products.removeIf(product -> product.getId() == id);
        int newSize = products.size();
        if (size != newSize) {
            baskets.get(1).setProducts(products);
            return true;
        }
        else {
            return false;
        }
    }
}

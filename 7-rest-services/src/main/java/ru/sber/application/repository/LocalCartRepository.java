package ru.sber.application.repository;

import org.springframework.stereotype.Repository;
import ru.sber.application.entity.Cart;
import ru.sber.application.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class LocalCartRepository implements CartRepository {

    List<Cart> carts = new ArrayList<>();
    ProductRepository productRepository;

    public LocalCartRepository( ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Cart createCart() {
        List<Product> products = new ArrayList<>();
        Cart cart = new Cart(generateId(), products, 0);
        carts.add(cart);
        return cart;
    }

    @Override
    public Optional<Cart> addToCart(long idCart, long idProduct) {
        Optional<Cart> cart = findById(idCart);
        Optional<Product> product = productRepository.findById(idProduct);
        if (cart.isPresent() && product.isPresent()) {
            cart.get().getProducts().add(product.get());
            return cart;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cart> findById(long id) {
        return carts.stream()
                .filter(cart -> cart.getId() == id)
                .findAny();
    }

    @Override
    public Optional<Cart> updateProductAmount(long idCart, long idProduct, int amount) {
        Optional<Cart> cart = findById(idCart);
        if (cart.isPresent()) {
            List<Product> products = cart.get().getProducts();
            Optional<Product> product = products.stream()
                    .filter(p -> p.getId() == idProduct)
                    .findAny();
            if (product.isPresent()) {
                product.get().setAmount(amount);
                return cart;
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteProduct(long idCart, long idProduct) {
        Optional<Cart> cart = findById(idCart);
        if (cart.isPresent()) {
            List<Product> products = cart.get().getProducts();
            return products.removeIf(product -> product.getId() == idProduct);
        }
        return false;
    }
    private long generateId() {
        Random random = new Random();
        int low = 1;
        int high = 1_000_000;
        return random.nextLong(high - low) + low;
    }
}

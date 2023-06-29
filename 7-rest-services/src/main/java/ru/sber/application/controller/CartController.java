package ru.sber.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.application.entity.Cart;
import ru.sber.application.entity.Product;
import ru.sber.application.repository.CartRepository;
import ru.sber.application.repository.ProductRepository;

import java.util.Optional;

@Slf4j
@RequestMapping("cart")
@RestController
public class CartController {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public CartController(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/{idCart}/product/{idProduct}")
    public ResponseEntity<Object> addToCart(@PathVariable long idCart, @PathVariable Long idProduct) {
        log.info("Добавление в корзину товара по id: {}", idProduct);
        Optional<Cart> cart = cartRepository.addToCart(idCart, idProduct);
        if (cart.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(cart.get());
        }
    }

    @PutMapping("/{idCart}/product/{idProduct}")
    public ResponseEntity<Cart> updateCart(@PathVariable long idCart, @PathVariable long idProduct, @RequestBody Product product) {
        log.info("Изменение количества товара в корзине");
        Optional<Cart> cart = cartRepository.updateProductAmount(idCart, idProduct, product.getAmount());
        if (cart.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(cart.get());
        }
    }

    @DeleteMapping("/{idCart}/product/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable long idCart, @PathVariable long idProduct) {
        log.info("Удаление из корзины товара по id: {}", idProduct);
        boolean isDeleted = cartRepository.deleteProduct(idCart, idProduct);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

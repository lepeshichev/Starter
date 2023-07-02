package ru.sber.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.application.entity.Cart;
import ru.sber.application.entity.Product;
import ru.sber.application.repository.CartRepository;
import ru.sber.application.repository.ProductRepository;

import java.net.URI;

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
    // todo вспомнить, что хотел сделать
    @PostMapping("/{idClient}/product/{idProduct}")
    public ResponseEntity<Cart> addToCart(@PathVariable long idClient, @PathVariable Long idProduct, @RequestBody Product product) {
        log.info("Добавление в корзину товара под id: {} в количестве {}", idProduct, product.getAmount());
        cartRepository.addToCart(idClient, idProduct, 10);
        return ResponseEntity.created(URI.create("cart/" + idClient + "/product/" + idProduct)).build();
    }

    @PutMapping("/{idCart}/product/{idProduct}")
    public ResponseEntity<Cart> updateCart(@PathVariable long idCart, @PathVariable long idProduct, @RequestBody Product product) {
        log.info("Изменение количества товара в корзине");
        boolean recordUpdated = cartRepository.updateAmount(idCart, idProduct, product.getAmount());
        if (recordUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
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

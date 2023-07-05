package ru.sber.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.demo.entity.Cart;
import ru.sber.demo.entity.Product;
import ru.sber.demo.services.CartService;
import ru.sber.demo.services.ProductService;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("cart")
public class CartController {
    CartService cartService;
    ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/{idClient}/product/{idProduct}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable long idClient, @PathVariable Long idProduct, @RequestBody Product product) {
        log.info("Добавление в корзину товара по id: {} количеством {}", idProduct, product.getAmount());
        boolean isRecordInserted = cartService.add(idClient, idProduct, product.getAmount());
        if (isRecordInserted) {
            return ResponseEntity.created(URI.create("cart/" + idClient + "/product/" + idProduct)).build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{idCart}/product/{idProduct}")
    public ResponseEntity<Cart> updateProductAmountInCart(@PathVariable long idCart, @PathVariable long idProduct, @RequestBody Product product) {
        log.info("Изменение количества товара в корзине");
        boolean recordUpdated = cartService.updateAmount(idCart, idProduct, product.getAmount());
        if (recordUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idCart}/product/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable long idCart, @PathVariable long idProduct) {
        log.info("Удаление из корзины товара по id: {}", idProduct);
        boolean isDeleted = cartService.deleteProduct(idCart, idProduct);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

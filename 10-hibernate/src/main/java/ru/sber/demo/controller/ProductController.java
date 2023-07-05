package ru.sber.demo.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.demo.entity.Product;
import ru.sber.demo.services.ProductService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        long productId = productService.addNewProduct(product);
        log.info("Добавление товара {}", product);
        return ResponseEntity.created(URI.create("products/" + productId)).build();
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String name) {
            log.info("Поиск товаров по имени {}", name);
        return productService.findAllByName(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable long id) {
        log.info("Получение товара по id");
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok().body(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product) {
        productService.update(product);
        log.info("Обновление информации о товаре");
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        log.info("Удаление продукта по id");
        boolean isDeleted = productService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

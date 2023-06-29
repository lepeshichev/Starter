package ru.sber.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.application.entity.Product;
import ru.sber.application.repository.ProductRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("products")
@RestController
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        log.info("Создание продукта {}", product);
        return ResponseEntity.created(URI.create("products/" + productRepository.save(product))).build();
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        log.info("Изменение продукта {}", product);
        productRepository.update(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        log.info("Удаление продукта по id");
        boolean isDeleted = productRepository.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        log.info("Получение продукта по id");
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok().body(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String name) {
        log.info("Поиск продуктов по имени {}", name);
        return productRepository.findAll(name);
    }
}

package ru.sber.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.application.entity.Product;
import ru.sber.application.repository.BasketRepository;

@Slf4j
@RequestMapping("basket")
@RestController
public class BasketController {
    private BasketRepository basketRepository;
    /*
    public BasketController(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }
    */
    @PostMapping
    public boolean add(@RequestBody Product product) {
        log.info("Добавление в корзину {}", product);
        return basketRepository.add(product);
    }

    @PutMapping
    public boolean updateBasket(@RequestBody Product product) {
        log.info("Изменение количества продукта в корзине");
        return basketRepository.update(10, 1);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable long id) {
        boolean isDeleted = basketRepository.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

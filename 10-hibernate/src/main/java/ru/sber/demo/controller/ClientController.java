package ru.sber.demo.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.demo.entity.Client;
import ru.sber.demo.services.CartService;
import ru.sber.demo.services.ClientService;

import java.net.URI;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("clients")
public class ClientController {
    private final ClientService clientService;
    private final CartService cartService;

    @Autowired
    public ClientController(ClientService clientService, CartService cartService) {
        this.clientService = clientService;
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<?> signUp(@Valid @RequestBody Client client) {
        long userId = clientService.signUp(client);
        log.info("Регистрация пользователя {}", client);
        return ResponseEntity.created(URI.create("users/" + userId)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getUserById(@PathVariable long id) {
        log.info("Выводим данные о пользователе по id: {}", id);
        Optional<Client> client = clientService.getUserById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok().body(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        log.info("Удаляем пользователя с id: {}", id);
        boolean isDeleted = clientService.deleteUserById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
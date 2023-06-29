package ru.sber.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.application.entity.Client;
import ru.sber.application.repository.ClientRepository;

import java.util.Optional;

@Slf4j
@RequestMapping("clients")
@RestController
public class ClientController {
    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping
    public long registerClient(@RequestBody Client client) {
        log.info("Регистрация клиента: {}", client);
        return clientRepository.register(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable long id) {
        log.info("Получение пользователя по id: {}", id);
        Optional<Client> client = clientRepository.getById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok().body(client.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable long id) {
        log.info("Удаляем пользователя по id: {}", id);
        boolean isDeleted = clientRepository.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

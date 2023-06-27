package ru.sber.application.repository;

import ru.sber.application.entity.Client;

import java.util.Optional;

public interface ClientRepository {
    long register(Client client);
    Optional<Client> getById(long id);
    boolean deleteById(long id);

}

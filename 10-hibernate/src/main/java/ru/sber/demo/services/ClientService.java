package ru.sber.demo.services;


import ru.sber.demo.entity.Client;

import java.util.Optional;

public interface ClientService {

    long signUp(Client user);

    Optional<Client> getUserById(long userId);

    boolean checkUserExistence(long userId);

    boolean deleteUserById(long userId);
}

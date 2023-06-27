package ru.sber.application.repository;

import org.springframework.stereotype.Repository;
import ru.sber.application.entity.Basket;
import ru.sber.application.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class LocalClientRepository implements ClientRepository {

    private List<Client> clients = new ArrayList<>(List.of(
            new Client(1, "first", "q1w2e3", "first@yandex.ru", new Basket(1, null, 3))
    ));


    @Override
    public long register(Client client) {
        long id = new Random().nextLong(999) + 1;;
        client.setId(id);
        clients.add(client);
        return id;
    }

    @Override
    public Optional<Client> getById(long id) {
        return clients.stream()
                .filter(client -> client.getId() == id)
                .findAny();
    }

    @Override
    public boolean deleteById(long id) {
        return clients.removeIf(client -> client.getId() == id);
    }
}

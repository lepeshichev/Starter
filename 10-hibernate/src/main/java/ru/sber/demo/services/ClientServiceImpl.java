package ru.sber.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sber.demo.entity.Client;
import ru.sber.demo.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CartService cartService;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CartService cartService) {
        this.clientRepository = clientRepository;
        this.cartService = cartService;
    }

    @Override
    public long signUp(Client client) {
        return clientRepository.save(client).getId();
    }

    @Override
    public Optional<Client> getUserById(long userId) {
        return clientRepository.findById(userId);
    }

    @Override
    public boolean checkUserExistence(long userId) {
        return clientRepository.existsById(userId);
    }

    @Override
    @Transactional
    public boolean deleteUserById(long userId) {
        if (checkUserExistence(userId)) {
            cartService.clearCart(userId);
            clientRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}

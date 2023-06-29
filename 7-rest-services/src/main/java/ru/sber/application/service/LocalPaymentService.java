package ru.sber.application.service;

import org.springframework.stereotype.Service;
import ru.sber.application.entity.Cart;
import ru.sber.application.entity.Client;
import ru.sber.application.entity.Payment;
import ru.sber.application.entity.Product;
import ru.sber.application.repository.LocalClientRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LocalPaymentService implements PaymentService {

    LocalClientRepository localClientRepository;

    public LocalPaymentService(LocalClientRepository localClientRepository) {
        this.localClientRepository = localClientRepository;
    }

    @Override
    public boolean pay(Payment payment) {
        Optional<Client> user = localClientRepository.getById(payment.getUserId());
        if (user.isPresent()) {
            Cart cart = user.get().getCart();
            List<Product> productList = cart.getProducts();
            if (productList != null) {
                BigDecimal price = productList.stream()
                        .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getAmount())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                if (payment.getSum() != null) {
                    if (payment.getSum().compareTo(price) >= 0) {
                        return true;
                    } else {
                        throw new RuntimeException("Недостаточно средств для оплаты товара");
                    }
                }
            }
        }
        return false;
    }
}


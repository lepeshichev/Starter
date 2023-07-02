package ru.sber.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sber.application.entity.Payment;
import ru.sber.application.proxies.BankApplicationInterface;
import ru.sber.application.repository.CartRepository;
import ru.sber.application.repository.ProductRepository;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private BankApplicationInterface bankApplicationInterface;

    public PaymentServiceImpl(CartRepository cartRepository,
                              ProductRepository productRepository,
                              BankApplicationInterface bankApplicationInterface) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.bankApplicationInterface = bankApplicationInterface;
    }
    @Override
    @Transactional
    public boolean pay(Payment payment) {
        long userId = payment.getUserId();
        BigDecimal totalCost = cartRepository.getCartPrice(userId);
        BigDecimal balance = bankApplicationInterface.getBalance(payment.getCardNumber());
        if (balance.compareTo(totalCost) >= 0) {
            productRepository.update(userId);
            cartRepository.clear(userId);
            return true;
        }
        return false;
    }
}

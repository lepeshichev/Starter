package ru.sber.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sber.demo.entity.Payment;
import ru.sber.demo.entity.Product;
import ru.sber.demo.proxies.BankApplicationInterface;

import java.math.BigDecimal;
import java.util.List;

/**
 * Сервис для взаимодействия с оплатой товаров
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final CartService cartService;
    private final ClientService clientService;
    private final ProductService productService;
    private final BankApplicationInterface bankApplicationInterface;

    @Autowired
    public PaymentServiceImpl(CartService cartService, ClientService clientService,
                              ProductService productService, BankApplicationInterface bankApplicationInterface1) {
        this.cartService = cartService;
        this.clientService = clientService;
        this.productService = productService;
        this.bankApplicationInterface = bankApplicationInterface1;

    }

    @Override
    @Transactional
    public boolean pay(Payment payment) {
        long userId = payment.getClientId();
        boolean userNotFound = !clientService.checkUserExistence(userId);

        List<Product> productsInCart = cartService.getListOfProductsInCart(userId);
        boolean productsNotEnough = checkProductsAmount(productsInCart);
        if (productsNotEnough) {
            throw new RuntimeException("Недостаточно продуктов в наличии");
        }
        if (cartService.countProductsInCart(userId) == 0) {
            throw new RuntimeException("Корзина пустая");
        }

        long cardNumber = payment.getCardNumber();
        BigDecimal sumOfCart = getSumOfCart(productsInCart);
        BigDecimal amountOfMoney = bankApplicationInterface.getBalance(cardNumber);

        if (amountOfMoney.compareTo(sumOfCart) >= 0) {
            updateAmountOfProducts(productsInCart);
            cartService.clearCart(userId);
            return true;
        }

        return false;
    }

    private boolean checkProductsAmount(List<Product> productsInCart) {
        for (Product product : productsInCart) {
            if (product.getAmount() > productService.findById(product.getId()).get().getAmount()) {
                return true;
            }
        }
        return false;
    }

    private BigDecimal getSumOfCart(List<Product> productsInCart) {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Product product : productsInCart) {
            BigDecimal amount = BigDecimal.valueOf(product.getAmount());
            BigDecimal price = productService.findById(product.getId()).get().getPrice();
            sum = sum.add(price.multiply(amount));
        }
        return sum;
    }
    private void updateAmountOfProducts(List<Product> productsInCart) {
        for (Product product : productsInCart) {
            Product updatedProduct = new Product();
            updatedProduct.setId(product.getId());
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setAmount(productService.findById(product.getId()).get().getAmount() - product.getAmount());
            productService.update(updatedProduct);
        }
    }
}
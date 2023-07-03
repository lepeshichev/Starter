package ru.sber.application.proxies;

import org.springframework.stereotype.Component;
import ru.sber.application.entity.Card;
import ru.sber.application.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BankApplication  implements BankApplicationInterface {

    List<Card> cards = new ArrayList<>(List.of(
            new Card(123, BigDecimal.valueOf(1000)),
            new Card(456, BigDecimal.valueOf(0))
    )) ;
    @Override
    public BigDecimal getBalance(long cardNumber) {
        Optional<Card> card = cards.stream()
                .filter(c -> c.getCardNumber() == cardNumber)
                .findAny();
        if (card.isPresent()) {
            return card.get().getBalance();
        }
        throw new RuntimeException("Такой карты нет");
    }
}

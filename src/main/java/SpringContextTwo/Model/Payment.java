package SpringContextTwo.Model;

import java.math.BigDecimal;

/**
 * Платеж с полями отправитель, получатель и сумма перевода
 */
public class Payment {
    private final String sender;
    private final String recipient;
    private final BigDecimal sum;

    public Payment(String sender, String recipient, BigDecimal sum) {
        this.sender = sender;
        this.recipient = recipient;
        this.sum = sum;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public BigDecimal getSum() {
        return sum;
    }
}

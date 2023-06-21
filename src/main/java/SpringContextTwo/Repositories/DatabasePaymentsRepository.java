package SpringContextTwo.Repositories;

import SpringContextTwo.Model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Наша база данных
 */
@Repository
public class DatabasePaymentsRepository {
    private final List<Payment> payments = new ArrayList<>();

    // Добавляем платеж в архив
    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> getPayments() {
        return payments;
    }
}

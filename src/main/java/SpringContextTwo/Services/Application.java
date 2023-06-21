package SpringContextTwo.Services;

import SpringContextTwo.Model.Payment;
import SpringContextTwo.Repositories.DatabasePaymentsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Наше приложение
 */
@Service
public class Application implements App {

    private final DatabasePaymentsRepository dpr = new DatabasePaymentsRepository();

    @Override
    public void SendMessage(Payment payment) {
        // Является ли пользователь клиентом банка
        if (BankClientsApp.sendAnswer(payment).equals("Является")) {
            // Переводим сумму по номеру телефона
            String result = TransferByPhoneApp.transferByPhoneNumber("123");
            // Добавляем перевод в базу данных
            dpr.addPayment(payment);
        }
        else
            // Выводим ошибку, если не является
            throw new RuntimeException("Not a client");
    }
}

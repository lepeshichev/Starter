package SpringContextTwo.Services;

import SpringContextTwo.Model.Payment;

/**
 * Банковское приложение для проверки, является ли плательщик клиентом банка
 */
public class BankClientsApp {
    // Простая логика проверки
    public static String sendAnswer(Payment payment) {
        System.out.println("Is client");
       return "Является";
    }
}

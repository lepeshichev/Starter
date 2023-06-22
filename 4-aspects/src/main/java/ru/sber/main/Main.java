package ru.sber.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sber.config.ProjectConfig;
import ru.sber.services.SomeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для тестирования
 */
public class Main {
    public static void main(String[] args) {
        var config = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var service = config.getBean(SomeService.class);
        List<String> list = new ArrayList<>();
        // Строки для тестов
        //service.printList(list);
        service.printString("asdasdas");
        //service.printString(null);
        //service.printString("");
        //list.add(null);
        //service.printList(list);
    }
}

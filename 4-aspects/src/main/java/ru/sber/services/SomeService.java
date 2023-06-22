package ru.sber.services;

import org.springframework.stereotype.Service;
import ru.sber.annotation.NotEmpty;

import java.util.List;

/**
 * Целевой сервис
 */
@Service
public class SomeService {
    @NotEmpty
    public void printList(List<String> list) {
        list.forEach(System.out::println);
    }

    @NotEmpty
    public void printString(String s) {
        System.out.println(s);
    }

}

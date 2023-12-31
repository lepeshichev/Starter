package ru.sber.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private long id;
    private String login;
    private String password;
    private String email;
    private Cart cart;
}

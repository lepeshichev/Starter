package ru.sber.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotEmpty(message = "Логин не заполнен")
    private String username;

    @Column(nullable = false)
    @NotEmpty(message = "Пароль не заполнен")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "Email не заполнен")
    private String email;
}

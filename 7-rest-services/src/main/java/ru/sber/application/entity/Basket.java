package ru.sber.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Basket {
    private long id;
    private List<Product> products;
    private long promoCode;
}

package ru.sber.application.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public class DBCartRepository implements CartRepository {

    private ClientRepository clientRepository;
    private JdbcTemplate jdbcTemplate;

    public DBCartRepository(ClientRepository clientRepository, JdbcTemplate jdbcTemplate) {
        this.clientRepository = clientRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addToCart(long idClient, long idProduct, int amount) {
        var addExpression = " insert into products_carts values(DEFAULT, ?, ?, ?)";
        var updateAmountExpression = """
                update products_carts
                set amount = amount + ?
                where id_cart = ? and id_product = ?;
                """;
        int rows = jdbcTemplate.update(updateAmountExpression, amount, idClient, idProduct);
        if (rows == 0) {
            return jdbcTemplate.update(addExpression, idProduct, idClient, amount) > 0;
        }
        return true;
    }
    @Override
    public boolean updateAmount(long idClient, long idProduct, int amount) {
        var updateExpression = "update products_carts set amount = ? where id_product = ? and id_cart = ?;";
        int rows = jdbcTemplate.update(updateExpression, amount, idProduct, idClient);
        return rows > 0;
    }

    @Override
    public boolean deleteProduct(long idClient, long idProduct) {
        var deleteExpression = "delete from products_carts where id_cart = ? and id_product = ?;";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            var preparedStatement = connection.prepareStatement(deleteExpression);
            preparedStatement.setLong(1, idClient);
            preparedStatement.setLong(2, idProduct);
            return preparedStatement;
        };
        int rows = jdbcTemplate.update(preparedStatementCreator);
        return rows > 0;
    }

    @Override
    public BigDecimal getCartPrice(long userId) {
        String countExpression = """
                select sum(p.price * pc.amount)
                from clients c, products p, products_carts pc
                where p.id = pc.id_product and pc.id_cart = c.cart_id and c.id = ?;
                """;
        boolean productsNotEnough = checkProductsQuantity(userId);
        if (productsNotEnough) {
            throw new RuntimeException("Нет столько товара");
        }
        return BigDecimal.valueOf(jdbcTemplate.queryForObject(countExpression, long.class, userId));
    }

    public boolean checkProductsQuantity(long userId) {
        String getAmountExpression = """
                select count(*)
                from products p, products_carts pc
                where p.id = pc.id_product and p.amount < pc.amount and id_cart = ?;
                """;
        Integer result = jdbcTemplate.queryForObject(getAmountExpression, Integer.class, userId);
        var nullCheck = Optional.ofNullable(result).orElse(0);
        return nullCheck > 0;
    }
    @Override
    public boolean clear(long userId) {
        String clearCartSql = "delete from products_carts where id_cart = ?;";
        int rows = jdbcTemplate.update(clearCartSql, userId);
        return rows > 0;
    }
}

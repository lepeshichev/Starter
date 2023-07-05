package ru.sber.application.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.sber.application.entity.Cart;
import ru.sber.application.entity.Client;
import ru.sber.application.entity.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;

@Repository
public class DBClientRepository implements ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long register(Client client) {
        var insertExpression = "insert into clients (username, password, email, cart_id) values (?, ?, ?, ?); ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement preparedStatement = con.prepareStatement(insertExpression, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getPassword());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setLong(4, generateCart());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return (long) (int) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public Optional<Client> getById(long id) {
        var selectClientExpression = "select * from clients where id = ?; ";
        PreparedStatementCreator preparedStatementCreator = con -> {
            var prepareStatement = con.prepareStatement(selectClientExpression);
            prepareStatement.setInt(1, (int) id);
            return prepareStatement;
        };
        RowMapper<Client> userRowMapper = (resultSet, rowNum) -> {
            int userId = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            int idCart = resultSet.getInt("cart_id");
            return new Client(userId, username, password, email, getCartById(idCart).get());
        };
        return jdbcTemplate.query(preparedStatementCreator, userRowMapper).stream().findAny();
    }

    @Override
    public boolean deleteById(long id) {
        var deleteClientExpression = "delete from clients where id = ?;";
        var deleteCartExpression = "delete from carts where id = ?; ";
        var deleteCartWithProductsExpression = "delete from products_carts where id_cart = ?; ";
        jdbcTemplate.update(deleteCartWithProductsExpression, id);
        var rows = jdbcTemplate.update(deleteClientExpression, id);
        jdbcTemplate.update(deleteCartExpression, id);
        return rows > 0;
    }

    private long generateCart() {
        var insertExpression = "insert into carts(promocode) values (?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement preparedStatement = con.prepareStatement(insertExpression, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "");
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return (long) (int) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    private Optional<Cart> getCartById(long idCart) {
        var selectExpression = "select * from products_carts pc, products p where pc.id_product = p.id and pc.id_cart = ?";
        var getCartExpression = " select * from carts where id = ?";
        PreparedStatementCreator productPreparedStatementCreator = con -> {
            var prepareStatement = con.prepareStatement(selectExpression);
            prepareStatement.setInt(1, (int) idCart);
            return prepareStatement;
        };
        PreparedStatementCreator cartPreparedStatementCreator = con -> {
            var prepareStatement = con.prepareStatement(getCartExpression);
            prepareStatement.setInt(1, (int) idCart);
            return prepareStatement;
        };
        RowMapper<Product> productRowMapper = (resultSet, rowNum) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            BigDecimal price = BigDecimal.valueOf(resultSet.getDouble("price"));
            int amount = resultSet.getInt("amount");
            return new Product(id, name, price, amount);
        };
        RowMapper<Cart> cartRowMapper = (resultSet, rowNum) -> new Cart(idCart, jdbcTemplate.query(productPreparedStatementCreator, productRowMapper), 0);
        return jdbcTemplate.query(cartPreparedStatementCreator, cartRowMapper).stream().findAny();
    }
}

package ru.sber.application.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.sber.application.entity.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class DBProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public long save(Product product) {
        var insertProductExpression = "insert into products(name, price, amount) values (?, ?, ?);";
        KeyHolder holder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement preparedStatement = con.prepareStatement(insertProductExpression, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice().doubleValue());
            preparedStatement.setInt(3, product.getAmount());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, holder);
        return (long) (int) Objects.requireNonNull(holder.getKeys()).get("id");
    }

    @Override
    public boolean update(Product product) {
        var updateExpression = "update products set name = ?, price = ?, amount = ? where id = ?;";
        PreparedStatementCreator preparedStatementCreator = con -> {
            var prepareStatement = con.prepareStatement(updateExpression);
            prepareStatement.setString(1, product.getName());
            prepareStatement.setDouble(2, product.getPrice().doubleValue());
            prepareStatement.setInt(3, product.getAmount());
            prepareStatement.setLong(4, product.getId());
            return prepareStatement;
        };
        int rows = jdbcTemplate.update(preparedStatementCreator);
        return rows > 0;
    }

    @Override
    public boolean deleteById(long id) {
        var deleteExpression = "delete from products where id = ?";
        PreparedStatementCreator preparedStatementCreator = con -> {
            var prepareStatement = con.prepareStatement(deleteExpression);
            prepareStatement.setLong(1, id);
            return prepareStatement;
        };
        int rows = jdbcTemplate.update(preparedStatementCreator);
        return rows > 0;
    }

    @Override
    public Optional<Product> findById(long productId) {
        var selectProductExpression = "select * from products where id = ?;";
        PreparedStatementCreator preparedStatementCreator = con -> {
            var preparedStatement = con.prepareStatement(selectProductExpression);
            preparedStatement.setLong(1, productId);
            return preparedStatement;
        };
        RowMapper<Product> productRowMapper = getProductRowMapper();
        List<Product> products = jdbcTemplate.query(preparedStatementCreator, productRowMapper);
        return products.stream().findFirst();
    }

    @Override
    public List<Product> findAll(String productName) {
        var selectAllProductsExpression = "select * from products where name like ?;";
        PreparedStatementCreator preparedStatementCreator = con -> {
            var prepareStatement = con.prepareStatement(selectAllProductsExpression);
            prepareStatement.setString(1, "%" + (productName == null ? "" : productName) + "%");
            return prepareStatement;
        };
        RowMapper<Product> productRowMapper = getProductRowMapper();
        return jdbcTemplate.query(preparedStatementCreator, productRowMapper);
    }

    private static RowMapper<Product> getProductRowMapper() {
        return (resultSet, rowNum) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int amount = resultSet.getInt("amount");
            return new Product(id, name, BigDecimal.valueOf(price), amount);
        };
    }
    @Override
    public void update(long userId) {
        var updateSqlExpression = """
                update products p
                set amount = amount - (select products_carts.amount from products_carts where id_product = p.id and id_cart = ?)
                where id in (select id_product from products_carts where id_cart = ?)
                """;
        PreparedStatementCreator preparedStatementCreator = con -> {
            var preparedStatement = con.prepareStatement(updateSqlExpression);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, userId);
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator);
    }
}

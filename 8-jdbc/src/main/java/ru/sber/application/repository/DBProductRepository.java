package ru.sber.application.repository;

import org.springframework.stereotype.Repository;
import ru.sber.application.entity.Product;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DBProductRepository implements ProductRepository {

    public static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=root";
    @Override
    public long save(Product product) {
        var insertProductExpression = """
                insert into products_lav.products(name, price, amount)
                values (?, ?, ?);
                """;
        try (var connection = DriverManager.getConnection(JDBC);
             var insertProductExpressionPrepareStatement = connection.
                     prepareStatement(insertProductExpression, Statement.RETURN_GENERATED_KEYS)) {
            insertProductExpressionPrepareStatement.setString(1, product.getName());
            insertProductExpressionPrepareStatement.setDouble(2, product.getPrice().doubleValue());
            insertProductExpressionPrepareStatement.setInt(3, product.getAmount());
            insertProductExpressionPrepareStatement.executeUpdate();
            ResultSet rs = insertProductExpressionPrepareStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("Ошибка при получении идентификатора");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Product product) {
        var updateExpression = "update products_lav.products set name = ?, price = ? where id = ?;";
        try (var connection = DriverManager.getConnection(JDBC);
             var updatePrepareStatement = connection.prepareStatement(updateExpression)) {
            updatePrepareStatement.setString(1, product.getName());
            updatePrepareStatement.setDouble(2, product.getPrice().doubleValue());
            updatePrepareStatement.setLong(3, product.getId());
            var rows = updatePrepareStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        var deleteExpression = "delete from products_lav.products where id = ?";
        try (var connection = DriverManager.getConnection(JDBC);
             var prepareDeleteStatement = connection.prepareStatement(deleteExpression)) {
            prepareDeleteStatement.setLong(1, id);
            var rows = prepareDeleteStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> findById(long productId) {
        var selectProductExpression = "select * from products_lav.products where id = ?;";
        try (var connection = DriverManager.getConnection(JDBC);
             var findProductByIdPrepareStatement = connection.prepareStatement(selectProductExpression)) {
            findProductByIdPrepareStatement.setLong(1, productId);
            var resultSet = findProductByIdPrepareStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int amount = resultSet.getInt("amount");
                Product product = new Product(id, name, BigDecimal.valueOf(price), amount);
                return Optional.of(product);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll(String productName) {
        var selectAllProductsExpression = "select * from products_lav.products where name like ?;";
        List<Product> products = new ArrayList<>();
        try (var connection = DriverManager.getConnection(JDBC);
             var selectAllProductsPrepareStatement = connection.prepareStatement(selectAllProductsExpression)) {
            selectAllProductsPrepareStatement.setString(1, "%" + (productName == null ? "" : productName) + "%");
            var resultSet = selectAllProductsPrepareStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int amount = resultSet.getInt("amount");
                Product product = new Product(id, name, BigDecimal.valueOf(price), amount);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

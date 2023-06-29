package ru.sber.application.repository;


import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class DBCartRepository implements CartRepository {

    public static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=root";

    ProductRepository productRepository;

    public DBCartRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean addToCart(long idClient, long idProduct, int amount) {
        var addExpression = " insert into products_lav.products_carts values(DEFAULT, ?, ?, ?)";
        try (var connection = DriverManager.getConnection(JDBC);
             var addPrepareStatement = connection.prepareStatement(addExpression, Statement.RETURN_GENERATED_KEYS)) {
                var product = productRepository.findById(idProduct);
            if (product.isPresent()) {
                addPrepareStatement.setLong(1, idProduct);
                addPrepareStatement.setLong(2, getIdCart(idClient));
                addPrepareStatement.setInt(3, amount);
                addPrepareStatement.executeUpdate();
                return addPrepareStatement.getGeneratedKeys().next();
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    public long getIdCart(long idClient) {
        var getExpression = "select * from products_lav.clients where id = ?;";
        try (var connection = DriverManager.getConnection(JDBC);
             var getPrepareStatement = connection.prepareStatement(getExpression)) {
                getPrepareStatement.setLong(1, idClient);
            var resultSet = getPrepareStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("cart_id");
            }
            throw new RuntimeException("Ошибка при получении идентификатора корзины");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean updateAmount(long idClient, long idProduct, int amount) {
        var updateExpression = "update products_lav.products_carts set amount = ? where id_product = ? and id_cart = ?;";
        try (var connection = DriverManager.getConnection(JDBC);
             var updatePrepareStatement = connection.prepareStatement(updateExpression)) {
                updatePrepareStatement.setDouble(1, amount);
                updatePrepareStatement.setLong(2, idProduct);
                updatePrepareStatement.setLong(3, getIdCart(idClient));
                var rows = updatePrepareStatement.executeUpdate();
                return rows > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteProduct(long idClient, long idProduct) {
        var deleteExpression = "delete from products_lav.products_carts where id_cart = ? and id_product = ?;";
        try (var connection = DriverManager.getConnection(JDBC);
             var deletePrepareStatement = connection.prepareStatement(deleteExpression)) {
            deletePrepareStatement.setLong(1, getIdCart(idClient));
            deletePrepareStatement.setLong(2, idProduct);
            var rows = deletePrepareStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

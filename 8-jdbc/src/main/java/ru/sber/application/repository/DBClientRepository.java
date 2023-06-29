package ru.sber.application.repository;

import org.springframework.stereotype.Repository;
import ru.sber.application.entity.Cart;
import ru.sber.application.entity.Client;
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
public class DBClientRepository implements ClientRepository {
    public static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=root";

    @Override
    public long register(Client client) {
        var insertExpression = """
                insert into products_lav.clients (username, password, email, cart_id)
                values (?, ?, ?, ?);
                """;
        try (var connection = DriverManager.getConnection(JDBC);
             var insertPrepareStatement = connection.prepareStatement(insertExpression, Statement.RETURN_GENERATED_KEYS)) {
            insertPrepareStatement.setString(1, client.getLogin());
            insertPrepareStatement.setString(2, client.getPassword());
            insertPrepareStatement.setString(3, client.getEmail());
            insertPrepareStatement.setLong(4, generateCart());
            insertPrepareStatement.executeUpdate();
            ResultSet rs = insertPrepareStatement.getGeneratedKeys();
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
    public Optional<Client> getById(long id) {
        var selectClientExpression = "select * from products_lav.clients where id = ?; ";
        var selectCartExpression = """
                select p.id, p.name, p.price, pc.amount
                from products_lav.products_carts pc, products_lav.products p
                where pc.id_product = p.id and pc.id_cart = ?;
                """;
        try (var connection = DriverManager.getConnection(JDBC);
             var selectUserStatement = connection.prepareStatement(selectClientExpression);
             var selectCartStatement = connection.prepareStatement(selectCartExpression)) {
            selectUserStatement.setLong(1, id);
            var resultSet = selectUserStatement.executeQuery();
            if (resultSet.next()) {
                var idClient = resultSet.getInt("id");
                var username = resultSet.getString("username");
                var email = resultSet.getString("email");
                var idCart = resultSet.getInt("cart_id");
                selectCartStatement.setLong(1, idCart);
                var resultProducts = selectCartStatement.executeQuery();
                List<Product> productList = new ArrayList<>();
                while (resultProducts.next()) {
                    int idProduct = resultProducts.getInt("id");
                    String nameProduct = resultProducts.getString("username");
                    BigDecimal priceProduct = BigDecimal.valueOf(resultProducts.getDouble("price"));
                    int amount = resultProducts.getInt("amount");
                    productList.add(new Product(idProduct, nameProduct, priceProduct, amount));
                }
                return Optional.of(new Client(idClient, username, "", email, new Cart(idCart, productList, 2)));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteById(long id) {
        var selectExpression = """
                select cart_id
                from products_lav.clients
                where id = ?;
                """;

        var deleteClientExpression = """
                delete from products_lav.clients
                where id = ?;
                """;
        var deleteCartExpression = """
                delete from products_lav.carts
                where id = ?;
                """;
        var deleteCartWithProductsExpression = """
                delete from products_lav.products_carts
                where id_cart = ?;
                """;
        try (var connection = DriverManager.getConnection(JDBC);
             var selectCartIdStatement = connection.prepareStatement(selectExpression);
             var deleteCartWithProductsStatement = connection.prepareStatement(deleteCartWithProductsExpression);
             var deleteCartStatement = connection.prepareStatement(deleteCartExpression);
             var deleteClientStatement = connection.prepareStatement(deleteClientExpression)) {
            selectCartIdStatement.setLong(1, id);
            deleteClientStatement.setLong(1, id);
            var resultSet = selectCartIdStatement.executeQuery();
            if (resultSet.next()) {
                long cartId = resultSet.getLong("cart_id");
                deleteCartWithProductsStatement.setLong(1, cartId);
                deleteCartStatement.setLong(1, cartId);
                deleteCartWithProductsStatement.executeUpdate();
                var rows = deleteClientStatement.executeUpdate();
                deleteCartStatement.executeUpdate();
                return rows > 0;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long generateCart() {
        var insertExpression = "insert into products_lav.carts(promocode) values (?);";
        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertExpression, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, "");
            prepareStatement.executeUpdate();
            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("Ошибка при получении идентификатора");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

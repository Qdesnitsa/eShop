package ru.clevertec.eshop.dao.source.database;

import ru.clevertec.eshop.dao.construction.EntityConstructor;
import ru.clevertec.eshop.dao.construction.ProductConstructor;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.ProductDAO;
import ru.clevertec.eshop.model.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAODatabase implements ProductDAO<Product> {
    EntityConstructor entityConstructor = new ProductConstructor();
    private static final String SQL_FIND_ALL_PRODUCTS =
            "SELECT products.id AS product_id, products.name, products.price, products.quantity, " +
                    "promos.id AS promo_id, promos.promo_name, promos.promo_value, promos.products_quantity " +
                    "FROM products LEFT JOIN promos ON products.promo_id = promos.id";

    private static final String SQL_FIND_PRODUCT_BY_ID =
            "SELECT products.id AS product_id, products.name, products.price, products.quantity, " +
                    "promos.id AS promo_id, promos.promo_name, promos.promo_value, promos.products_quantity " +
                    "FROM products LEFT JOIN promos ON products.promo_id = promos.id WHERE products.id = ?";
    @Override
    public List<Product> findAll() throws DAOException {
        List<Product> products = new ArrayList<>();
        ResultSet resultSet = null;
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PRODUCTS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add((Product) entityConstructor.constructEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find all products in the database", e);
        }
        return products;
    }

    @Override
    public Optional<Product> findByID(Long entityId) throws DAOException {
        Optional<Product> optional = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRODUCT_BY_ID)) {
            statement.setLong(1, entityId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = (Product) entityConstructor.constructEntity(resultSet);
                optional = Optional.of(product);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find product by product ID in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return optional;
    }
}

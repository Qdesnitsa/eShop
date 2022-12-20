package ru.clevertec.eshop.dao.source.database;

import ru.clevertec.eshop.dao.construction.EntityConstructor;
import ru.clevertec.eshop.dao.construction.PromoConstructor;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.PromoDAO;
import ru.clevertec.eshop.model.promo.Promo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromoDAODatabase implements PromoDAO<Promo> {
    EntityConstructor entityConstructor = new PromoConstructor();
    private static final String SQL_FIND_ALL_PROMOS =
            "SELECT * FROM promos";
    private static final String SQL_FIND_PROMO_BY_ID =
            "SELECT * FROM promos WHERE id = ?";
    @Override
    public List<Promo> findAll() throws DAOException {
        List<Promo> promos = new ArrayList<>();
        ResultSet resultSet = null;
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PROMOS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                promos.add((Promo) entityConstructor.constructEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find all products in the database", e);
        }
        return promos;
    }

    @Override
    public Optional<Promo> findByID(Long entityId) throws DAOException {
        Optional<Promo> promo = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement((SQL_FIND_PROMO_BY_ID))) {
            statement.setLong(1,entityId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                promo = Optional.ofNullable((Promo) entityConstructor.constructEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find check by ID in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return promo;
    }
}

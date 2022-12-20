package ru.clevertec.eshop.dao.source.database;

import ru.clevertec.eshop.dao.construction.CardConstructor;
import ru.clevertec.eshop.dao.construction.EntityConstructor;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.CardDAO;
import ru.clevertec.eshop.model.card.DiscountCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDAODatabase implements CardDAO<DiscountCard> {
    private static final String SQL_FIND_ALL_CARDS =
            "SELECT * FROM discount_cards";
    private static final String SQL_FIND_CARD_BY_ID =
            "SELECT * FROM discount_cards WHERE id = ?";
    private static final String SQL_FIND_CARD_BY_NUMBER =
            "SELECT * FROM discount_cards WHERE card_number = ?";
    EntityConstructor entityConstructor = new CardConstructor();

    @Override
    public List<DiscountCard> findAll() throws DAOException {
        List<DiscountCard> cards = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_CARDS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cards.add((DiscountCard) entityConstructor.constructEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find all products in the database", e);
        }
        return cards;
    }

    @Override
    public Optional<DiscountCard> findByID(Long entityId) throws DAOException {
        Optional<DiscountCard> discountCard = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement((SQL_FIND_CARD_BY_ID))) {
            statement.setLong(1, entityId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                discountCard = Optional.ofNullable((DiscountCard) entityConstructor.constructEntity(resultSet));
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
        return discountCard;
    }

    @Override
    public Optional<DiscountCard> findCardByNumber(int cardNumber) throws DAOException {
        Optional<DiscountCard> discountCard = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement((SQL_FIND_CARD_BY_NUMBER))) {
            statement.setInt(1, cardNumber);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                discountCard = Optional.ofNullable((DiscountCard) entityConstructor.constructEntity(resultSet));
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
        return discountCard;
    }
}

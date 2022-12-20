package ru.clevertec.eshop.dao.source;

import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.model.card.DiscountCard;

import java.util.List;
import java.util.Optional;

public interface CardDAO<T>{
    List<T> findAll() throws DAOException;
    Optional<T> findByID(Long entityId) throws DAOException;
    Optional<DiscountCard> findCardByNumber(int cardNumber) throws DAOException;
}

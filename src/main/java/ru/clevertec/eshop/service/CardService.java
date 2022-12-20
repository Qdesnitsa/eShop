package ru.clevertec.eshop.service;

import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CardService<T> {
    List<T> findAll() throws ServiceException;
    Optional<T> findByID(Long entityId) throws ServiceException;
    Optional<DiscountCard> findCardByNumber(int cardNumber) throws ServiceException;
    Optional<DiscountCard> obtainValidatedCard(String[] args) throws ServiceException;
}

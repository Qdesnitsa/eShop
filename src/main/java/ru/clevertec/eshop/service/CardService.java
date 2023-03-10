package ru.clevertec.eshop.service;

import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CardService<T> {
    List<T> findAll() throws ServiceException;
    Optional<T> findByID(Long entityId) throws ServiceException;
    Optional<T> findCardByNumber(int cardNumber) throws ServiceException;
    Optional<T> obtainValidatedCard(String[] args) throws ServiceException;
}

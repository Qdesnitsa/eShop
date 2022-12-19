package ru.clevertec.eshop.dao.impl.file;

import ru.clevertec.eshop.model.card.DiscountCard;

import java.util.List;
import java.util.Optional;

public interface CardDAO<T>{
    List<T> findAll();
    Optional<T> findByID(Long entityId);
    Optional<DiscountCard> findCardByNumber(int cardNumber);
}

package ru.clevertec.eshop.dao.source;

import ru.clevertec.eshop.dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface PromoDAO<T> {
    List<T> findAll() throws DAOException;
    Optional<T> findByID(Long entityId) throws DAOException;
}

package ru.clevertec.eshop.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    List<T> findAll();
    Optional<T> findByID(Long entityId);
}

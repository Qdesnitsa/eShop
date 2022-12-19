package ru.clevertec.eshop.dao.impl.file;

import java.util.List;
import java.util.Optional;

public interface PromoDAO<T> {
    List<T> findAll();
    Optional<T> findByID(Long entityId);
}

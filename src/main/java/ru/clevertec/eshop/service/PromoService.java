package ru.clevertec.eshop.service;

import java.util.List;
import java.util.Optional;

public interface PromoService<T> {
    List<T> findAll();
    Optional<T> findByID(Long entityId);
}

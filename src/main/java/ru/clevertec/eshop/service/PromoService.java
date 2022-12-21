package ru.clevertec.eshop.service;

import ru.clevertec.eshop.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface PromoService<T> {
    List<T> findAll() throws ServiceException;
    Optional<T> findByID(Long entityId);
}

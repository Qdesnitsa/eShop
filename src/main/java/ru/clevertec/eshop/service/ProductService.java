package ru.clevertec.eshop.service;

import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ProductService<T> {
    List<T> findAll();
    Optional<T> findByID(Long entityId);
    List<Product> obtainValidatedProducts(String[] args) throws ServiceException;
}

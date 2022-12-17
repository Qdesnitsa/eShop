package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> find(Long id);
}

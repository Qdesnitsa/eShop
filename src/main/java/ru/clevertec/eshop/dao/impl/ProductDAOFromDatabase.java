package ru.clevertec.eshop.dao.impl;

import ru.clevertec.eshop.dao.ProductDAO;
import ru.clevertec.eshop.model.Product;

import java.util.List;

public class ProductDAOFromDatabase implements ProductDAO {
    @Override
    public List<Product> find(Long id) {
        return null;
    }
}

package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.model.product.Product;

import java.util.List;
import java.util.Map;

public interface ProductBaseDAO extends BaseDAO<Product> {
    List<Product> findByIdAndQuantity(Map<Object, Integer> map);

}

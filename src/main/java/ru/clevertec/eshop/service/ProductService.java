package ru.clevertec.eshop.service;

import ru.clevertec.eshop.model.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProducts(Long productId, int requiredQuantity);
}

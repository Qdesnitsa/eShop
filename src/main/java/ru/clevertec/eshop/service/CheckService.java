package ru.clevertec.eshop.service;

import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.model.product.Product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CheckService<T, S> {
    boolean save(S entity) throws IOException;
    String obtainCheck(List<Product> products, Optional<DiscountCard> card);
}

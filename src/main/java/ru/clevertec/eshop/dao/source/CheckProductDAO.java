package ru.clevertec.eshop.dao.source;

import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.model.Check;
import ru.clevertec.eshop.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface CheckProductDAO {
    boolean save(List<Product> products, Optional<Check> check) throws DAOException;
}

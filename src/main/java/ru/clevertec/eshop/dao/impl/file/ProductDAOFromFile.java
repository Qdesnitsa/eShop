package ru.clevertec.eshop.dao.impl.file;

import ru.clevertec.eshop.dao.BaseDAO;

import java.util.List;
import java.util.Optional;

public class ProductDAOFromFile implements BaseDAO {
    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Optional findByID(Long productId) {
        return Optional.empty();
    }
}

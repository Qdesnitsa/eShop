package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.dao.impl.ProductDAOFromDatabase;
import ru.clevertec.eshop.dao.impl.ProductDAOFromFile;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final ProductDAO productDAOFromFile = new ProductDAOFromFile();
    private final ProductDAO productDAOFromDatabase = new ProductDAOFromDatabase();

    private DAOFactory() {}

    public ProductDAO getProductDAOFromFile() {
        return productDAOFromFile;
    }
    public ProductDAO getProductDAOFromDatabase() {
        return productDAOFromDatabase;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}

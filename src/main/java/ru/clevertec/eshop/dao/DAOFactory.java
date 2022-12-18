package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.dao.impl.database.ProductDAOFromDatabase;
import ru.clevertec.eshop.dao.impl.file.CardDAOFromFile;
import ru.clevertec.eshop.dao.impl.file.ProductDAOFromFile;
import ru.clevertec.eshop.dao.impl.file.PromoDAOFromFile;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final BaseDAO productDAOFromFile = new ProductDAOFromFile();
    private final BaseDAO cardDAOFromFile = new CardDAOFromFile();
    private final BaseDAO promoDAOFromFile = new PromoDAOFromFile();
    private final BaseDAO productDAOFromDatabase = new ProductDAOFromDatabase();

    private DAOFactory() {
    }

    public BaseDAO getProductDAOFromFile() {
        return productDAOFromFile;
    }

    public BaseDAO getCardDAOFromFile() {
        return cardDAOFromFile;
    }

    public BaseDAO getPromoDAOFromFile() {
        return promoDAOFromFile;
    }

    public BaseDAO getProductDAOFromDatabase() {
        return productDAOFromDatabase;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}

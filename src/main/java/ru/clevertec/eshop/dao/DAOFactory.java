package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.dao.impl.file.CardDAO;
import ru.clevertec.eshop.dao.impl.file.ProductDAO;
import ru.clevertec.eshop.dao.impl.file.PromoDAO;
import ru.clevertec.eshop.dao.impl.file.impl.CardDAOFromFile;
import ru.clevertec.eshop.dao.impl.file.impl.ProductDAOFromFile;
import ru.clevertec.eshop.dao.impl.file.impl.PromoDAOFromFile;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final ProductDAO productDAOFromFile = new ProductDAOFromFile();
    private final CardDAO cardDAOFromFile = new CardDAOFromFile();
    private final PromoDAO promoDAOFromFile = new PromoDAOFromFile();
    //private final BaseDAO productDAOFromDatabase = new ProductDAOFromDatabase();

    private DAOFactory() {
    }

    public ProductDAO getProductDAOFromFile() {
        return productDAOFromFile;
    }

    public CardDAO getCardDAOFromFile() {
        return cardDAOFromFile;
    }

    public PromoDAO getPromoDAOFromFile() {
        return promoDAOFromFile;
    }

//    public BaseDAO getProductDAOFromDatabase() {
//        return productDAOFromDatabase;
//    }

    public static DAOFactory getInstance() {
        return instance;
    }
}

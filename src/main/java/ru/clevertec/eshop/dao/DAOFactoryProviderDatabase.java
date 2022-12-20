package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.dao.source.CardDAO;
import ru.clevertec.eshop.dao.source.CheckDAO;
import ru.clevertec.eshop.dao.source.ProductDAO;
import ru.clevertec.eshop.dao.source.PromoDAO;
import ru.clevertec.eshop.dao.source.database.CardDAODatabase;
import ru.clevertec.eshop.dao.source.database.CheckDAODatabase;
import ru.clevertec.eshop.dao.source.database.ProductDAODatabase;
import ru.clevertec.eshop.dao.source.database.PromoDAODatabase;

public class DAOFactoryProviderDatabase implements DAOFactoryProvider {
    private final ProductDAO productDAOFromDB = new ProductDAODatabase();
    private final CardDAO cardDAOFromDB = new CardDAODatabase();
    private final PromoDAO promoDAOFromDB = new PromoDAODatabase();
    private final CheckDAO checkDAOFromDB = new CheckDAODatabase();
    @Override
    public ProductDAO getProductDAO() {
        return productDAOFromDB;
    }

    @Override
    public CardDAO getCardDAO() {

        return cardDAOFromDB;
    }

    @Override
    public PromoDAO getPromoDAO() {

        return promoDAOFromDB;
    }

    @Override
    public CheckDAO getCheckDAO() {

        return checkDAOFromDB;
    }
}

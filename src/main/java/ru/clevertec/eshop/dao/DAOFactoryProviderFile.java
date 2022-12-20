package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.dao.source.CardDAO;
import ru.clevertec.eshop.dao.source.CheckDAO;
import ru.clevertec.eshop.dao.source.ProductDAO;
import ru.clevertec.eshop.dao.source.PromoDAO;
import ru.clevertec.eshop.dao.source.file.CardDAOFromFile;
import ru.clevertec.eshop.dao.source.file.CheckDAOFromFile;
import ru.clevertec.eshop.dao.source.file.ProductDAOFromFile;
import ru.clevertec.eshop.dao.source.file.PromoDAOFromFile;

public class DAOFactoryProviderFile implements DAOFactoryProvider {
    private final ProductDAO productDAOFromFile = new ProductDAOFromFile();
    private final CardDAO cardDAOFromFile = new CardDAOFromFile();
    private final PromoDAO promoDAOFromFile = new PromoDAOFromFile();
    private final CheckDAO checkDAOFromFile = new CheckDAOFromFile();
    @Override
    public ProductDAO getProductDAO() {
        return productDAOFromFile;
    }

    @Override
    public CardDAO getCardDAO() {
        return cardDAOFromFile;
    }

    @Override
    public PromoDAO getPromoDAO() {
        return promoDAOFromFile;
    }

    @Override
    public CheckDAO getCheckDAO() {
        return checkDAOFromFile;
    }
}

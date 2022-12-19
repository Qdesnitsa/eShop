package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.dao.impl.file.CardDAO;
import ru.clevertec.eshop.dao.impl.file.CheckDAO;
import ru.clevertec.eshop.dao.impl.file.ProductDAO;
import ru.clevertec.eshop.dao.impl.file.PromoDAO;
import ru.clevertec.eshop.dao.impl.file.impl.CardDAOFromFile;
import ru.clevertec.eshop.dao.impl.file.impl.CheckDAOFromFile;
import ru.clevertec.eshop.dao.impl.file.impl.ProductDAOFromFile;
import ru.clevertec.eshop.dao.impl.file.impl.PromoDAOFromFile;

public class FactoryProviderFile implements FactoryProvider {
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

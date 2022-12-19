package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.dao.impl.file.CardDAO;
import ru.clevertec.eshop.dao.impl.file.CheckDAO;
import ru.clevertec.eshop.dao.impl.file.ProductDAO;
import ru.clevertec.eshop.dao.impl.file.PromoDAO;
import ru.clevertec.eshop.dao.impl.file.impl.CardDAOFromFile;
import ru.clevertec.eshop.dao.impl.file.impl.CheckDAOFromFile;
import ru.clevertec.eshop.dao.impl.file.impl.ProductDAOFromFile;
import ru.clevertec.eshop.dao.impl.file.impl.PromoDAOFromFile;

public class FactoryProviderDatabase implements FactoryProvider {
//    private final ProductDAO productDAOFromDB = new ProductDAOFromFile();
//    private final CardDAO cardDAOFromDB = new CardDAOFromFile();
//    private final PromoDAO promoDAOFromDB = new PromoDAOFromFile();
//    private final CheckDAO checkDAOFromDB = new CheckDAOFromFile();
    @Override
    public ProductDAO getProductDAO() {
        return null;
    }

    @Override
    public CardDAO getCardDAO() {
        return null;
    }

    @Override
    public PromoDAO getPromoDAO() {
        return null;
    }

    @Override
    public CheckDAO getCheckDAO() {
        return null;
    }
}

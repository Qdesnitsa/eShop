package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.dao.source.CardDAO;
import ru.clevertec.eshop.dao.source.CheckDAO;
import ru.clevertec.eshop.dao.source.ProductDAO;
import ru.clevertec.eshop.dao.source.PromoDAO;

public interface DAOFactoryProvider {
    ProductDAO getProductDAO();

    CardDAO getCardDAO();

    PromoDAO getPromoDAO();

    CheckDAO getCheckDAO();
}

package ru.clevertec.eshop.dao;

import ru.clevertec.eshop.dao.impl.file.CardDAO;
import ru.clevertec.eshop.dao.impl.file.CheckDAO;
import ru.clevertec.eshop.dao.impl.file.ProductDAO;
import ru.clevertec.eshop.dao.impl.file.PromoDAO;

public interface FactoryProvider {
    ProductDAO getProductDAO();

    CardDAO getCardDAO();

    PromoDAO getPromoDAO();

    CheckDAO getCheckDAO();
}

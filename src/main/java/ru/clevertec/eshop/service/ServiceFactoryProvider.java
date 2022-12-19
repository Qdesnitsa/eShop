package ru.clevertec.eshop.service;

public interface ServiceFactoryProvider {
    ProductService getProductService();

    PromoService getPromoService();

    CardService getCardService();

    CheckService getCheckService();
}

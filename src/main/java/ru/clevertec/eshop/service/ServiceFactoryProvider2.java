package ru.clevertec.eshop.service;

import ru.clevertec.eshop.service.impl.CardServiceImpl;
import ru.clevertec.eshop.service.impl.CheckServiceImpl;
import ru.clevertec.eshop.service.impl.ProductServiceImpl;
import ru.clevertec.eshop.service.impl.PromoServiceImpl;

public class ServiceFactoryProvider2 implements ServiceFactoryProvider {
    private final ProductService productService = new ProductServiceImpl();
    private final PromoService promoService = new PromoServiceImpl();
    private final CardService cardService = new CardServiceImpl();
    private final CheckService checkService = new CheckServiceImpl();
    @Override
    public ProductService getProductService() {
        return productService;
    }

    @Override
    public PromoService getPromoService() {
        return promoService;
    }

    @Override
    public CardService getCardService() {
        return cardService;
    }

    @Override
    public CheckService getCheckService() {
        return checkService;
    }
}

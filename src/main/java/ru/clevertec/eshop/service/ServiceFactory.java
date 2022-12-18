package ru.clevertec.eshop.service;

import ru.clevertec.eshop.service.impl.ProductServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final ProductService productService = new ProductServiceImpl();

    private ServiceFactory() {}

    public ProductService getProductService() {

        return productService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}

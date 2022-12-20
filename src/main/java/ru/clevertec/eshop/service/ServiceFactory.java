package ru.clevertec.eshop.service;

public final class ServiceFactory {
    private static final ServiceFactoryProvider instance = new ServiceFactoryProviderFirst();

    private ServiceFactory() {
    }

    public static ServiceFactoryProvider getInstance() {
        return instance;
    }
}

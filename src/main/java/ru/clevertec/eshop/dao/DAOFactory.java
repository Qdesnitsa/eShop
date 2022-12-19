package ru.clevertec.eshop.dao;

public final class DAOFactory {
    private static final FactoryProvider instance = new FactoryProviderFile();

    private DAOFactory() {
    }

    public static FactoryProvider getInstance() {
        return instance;
    }
}

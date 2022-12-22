package ru.clevertec.eshop.dao;

public final class DAOFactory {
    // ability to change implementation:
    // get data source from file = new DAOFactoryProviderFile()
    // get data source from database = new DAOFactoryProviderDatabase()
    private static final DAOFactoryProvider instance = new DAOFactoryProviderDatabase();

    private DAOFactory() {
    }

    public static DAOFactoryProvider getInstance() {
        return instance;
    }
}

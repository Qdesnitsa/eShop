package ru.clevertec.eshop.dao.impl.database;

import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.util.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String DB_PROPERTIES = "application.properties";
    private static final Properties properties = PropertiesReader.getProperties(DB_PROPERTIES);
    private static final String URL = properties.getProperty("db.url");
    private static final String PASSWORD = properties.getProperty("db.password");
    private static final String USER = properties.getProperty("db.username");
    private static final String DRIVER = properties.getProperty("db.driver");


    public static Connection getConnection() throws DAOException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Can not find database driver", e);
        } catch (SQLException ex) {
            throw new DAOException("Can not get connection", ex);
        }
    }
}

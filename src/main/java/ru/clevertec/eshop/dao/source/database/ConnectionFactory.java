package ru.clevertec.eshop.dao.source.database;

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


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            //LOGGER.error(e);
        } catch (SQLException ex) {
            //LOGGER.error(e);
        }
        return connection;
    }
}

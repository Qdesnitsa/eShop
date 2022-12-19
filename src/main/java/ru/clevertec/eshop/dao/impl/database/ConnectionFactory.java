package ru.clevertec.eshop.dao.impl.database;

import ru.clevertec.eshop.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static String url = "jdbc:postgresql://localhost:5432/eshop";
    private static String password = "2cool4u";
    private static String user = "postgres";
    private static String driver = "org.postgresql.Driver";
    public static Connection getConnection() throws DAOException {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Can not find database driver", e);
        } catch (SQLException ex) {
            throw new DAOException("Can not get connection", ex);
        }
    }
}

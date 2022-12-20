package ru.clevertec.eshop.dao.construction;

import ru.clevertec.eshop.dao.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface EntityConstructor<T> {
    T constructEntity(Map<String, Object> map) throws DAOException;
    T constructEntity(ResultSet resultSet) throws SQLException;
}

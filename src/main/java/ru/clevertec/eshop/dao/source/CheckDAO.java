package ru.clevertec.eshop.dao.source;

import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.model.Check;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public interface CheckDAO<T> {
    boolean save(T entity) throws IOException, DAOException;
    Optional<Check> findById (Long id) throws DAOException;
    Optional<Check> findByLastId () throws DAOException;
}

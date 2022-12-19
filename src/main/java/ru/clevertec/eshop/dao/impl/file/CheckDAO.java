package ru.clevertec.eshop.dao.impl.file;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CheckDAO<T,S> {
    boolean save(S entity) throws IOException;
}

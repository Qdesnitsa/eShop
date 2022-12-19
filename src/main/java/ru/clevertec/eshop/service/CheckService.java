package ru.clevertec.eshop.service;

import java.io.IOException;

public interface CheckService<T, S> {
    boolean save(S entity) throws IOException;
}

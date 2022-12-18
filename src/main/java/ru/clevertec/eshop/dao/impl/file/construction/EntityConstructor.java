package ru.clevertec.eshop.dao.impl.file.construction;

import java.util.Map;

public interface EntityConstructor<T> {
    T constructEntity(Map<String, Object> map);
}

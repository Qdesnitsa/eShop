package ru.clevertec.eshop.dao.impl.file.impl;

import ru.clevertec.eshop.dao.impl.file.CheckDAO;
import ru.clevertec.eshop.model.Check;
import ru.clevertec.eshop.util.AppConstant;

import java.io.*;

public class CheckDAOFromFile implements CheckDAO<Check, String> {
    private final String filePath = AppConstant.CHECKS_FILE;

    @Override
    public boolean save(String entity) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(entity);
            writer.append("\n\n\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to checks info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return true;
    }
}

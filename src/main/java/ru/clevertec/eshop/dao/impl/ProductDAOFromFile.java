package ru.clevertec.eshop.dao.impl;

import ru.clevertec.eshop.dao.ProductDAO;
import ru.clevertec.eshop.model.Product;
import ru.clevertec.eshop.util.AppConstant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAOFromFile implements ProductDAO {
    private static final String DISCOUNTS_FILE = AppConstant.DISCOUNTS_FILE;
    private static final String CARDS_FILE = AppConstant.CARDS_FILE;
    private static final String PRODUCTS_FILE = AppConstant.PRODUCTS_FILE;

    private static BufferedReader reader;
    private static File file;
    private static FileReader fileReader;
    private static Map<String, Object> properAppFromDB = new HashMap<>();
    @Override
    public List<Product> find(Long id) {
        return null;
    }
}

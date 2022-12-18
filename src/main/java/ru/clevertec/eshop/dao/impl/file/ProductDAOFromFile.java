package ru.clevertec.eshop.dao.impl.file;

import ru.clevertec.eshop.dao.BaseDAO;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.impl.file.construction.CardConstructor;
import ru.clevertec.eshop.dao.impl.file.construction.ProductConstructor;
import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.util.AppConstant;
import ru.clevertec.eshop.util.parsing.DataParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProductDAOFromFile implements BaseDAO {
    private final String filePath = Objects.requireNonNull(getClass().getClassLoader()
            .getResource(AppConstant.PRODUCTS_FILE)).getPath();

    @Override
    public List<Product> findAll() {
        List<Product> cardsList = new ArrayList<>();

        Map<String, Object> productMap;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                productMap = DataParser.obtainMap(line);
                cardsList.add(createProduct(productMap));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to discounts info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return cardsList;
    }

    @Override
    public Optional<Product> findByID(Long productId) {
        Map<String, Object> productMap;
        Product product = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                productMap = DataParser.obtainMap(line);
                if (productMap.get(SearchCriteria.Card.ID) == productId) {
                    product = createProduct(productMap);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to cards info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return Optional.ofNullable(product);
    }

    private Product createProduct(Map<String, Object> map) {
        Product product = new ProductConstructor().constructEntity(map);
        return product;
    }
}

package ru.clevertec.eshop.dao.source.file;

import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.ProductDAO;
import ru.clevertec.eshop.dao.construction.ProductConstructor;
import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.exception.ServiceException;
import ru.clevertec.eshop.util.AppConstant;
import ru.clevertec.eshop.util.parsing.DataParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Long.parseLong;

public class ProductDAOFromFile implements ProductDAO<Product> {
    private final String filePath = Objects.requireNonNull(getClass().getClassLoader()
            .getResource(AppConstant.PRODUCTS_FILE)).getPath();

    @Override
    public List<Product> findAll() throws DAOException {
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
            throw new DAOException("Check file path to discounts info. File is not fount", e);
        } catch (IOException e) {
            throw new DAOException("Error in getting data from file", e);
        }
        return cardsList;
    }

    @Override
    public Optional<Product> findByID(Long productId) throws DAOException {
        Map<String, Object> productMap;
        Product product = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready() && product == null) {
                line = reader.readLine();
                productMap = DataParser.obtainMap(line);
                if (parseLong((String) productMap.get(SearchCriteria.Product.ID.toString())) == (productId)) {
                    product = createProduct(productMap);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DAOException("Check file path to products info. File is not fount", e);
        } catch (IOException e) {
        throw new DAOException("Error in getting data from file", e);
        }
        return Optional.ofNullable(product);
    }

    private Product createProduct(Map<String, Object> map) {
        Product product = new ProductConstructor().constructEntity(map);
        return product;
    }
}

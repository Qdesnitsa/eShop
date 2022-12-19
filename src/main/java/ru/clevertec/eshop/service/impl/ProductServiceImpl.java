package ru.clevertec.eshop.service.impl;

import ru.clevertec.eshop.dao.DAOFactory;
import ru.clevertec.eshop.dao.FactoryProvider;
import ru.clevertec.eshop.dao.impl.file.CheckDAO;
import ru.clevertec.eshop.dao.impl.file.ProductDAO;
import ru.clevertec.eshop.dao.impl.file.impl.ProductDAOFromFile;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.ProductService;
import ru.clevertec.eshop.service.exception.ServiceException;
import ru.clevertec.eshop.service.validation.DataValidator;
import ru.clevertec.eshop.util.parsing.DataParser;

import java.util.*;

import static java.lang.Integer.valueOf;
import static java.lang.Long.parseLong;

public class ProductServiceImpl implements ProductService<Product> {
    private static final String CARD = "card";
    FactoryProvider factoryProvider = DAOFactory.getInstance();
    ProductDAO productDAO = factoryProvider.getProductDAO();

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Optional<Product> findByID(Long entityId) {
        return productDAO.findByID(entityId);
    }

    public List<Product> obtainValidatedProducts(String[] args) throws ServiceException {
        Map<String, Integer> map = obtainValidatedCriteria(args);
        List<Product> correctProductsId = checkExistingProductId(map);
        List<Product> correctProductsQuantityAndId = checkExistingProductQuantity(correctProductsId, map);
        return correctProductsQuantityAndId;
    }

    public Map<String, Integer> obtainValidatedCriteria(String[] args) throws ServiceException {
        List<String> formattedArgs = DataParser.parseLine(args);
        List<String> validatedCriteria = DataValidator.validateCriteria(formattedArgs);
        Map<String, Integer> validatedMap = new HashMap<>();
        for (int i = 0; i < validatedCriteria.size(); i += 2) {
            if (validatedMap.containsKey(validatedCriteria.get(i))) {
                validatedMap.put(validatedCriteria.get(i),
                        valueOf(validatedMap.get(validatedCriteria.get(i))) + valueOf(validatedCriteria.get(i + 1)));
            } else {
                validatedMap.put(validatedCriteria.get(i), valueOf(validatedCriteria.get(i + 1)));
            }
        }
        return validatedMap;
    }

    public List<Product> checkExistingProductId(Map<String, Integer> inputCriteria) throws ServiceException {
        List<Product> productList = new ArrayList<>();
        List<Long> notExistingProductId = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inputCriteria.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(CARD)) {
                Optional<Product> product = productDAO.findByID(parseLong(entry.getKey()));
                if (product.isPresent()) {
                    productList.add(product.get());
                } else {
                    notExistingProductId.add(parseLong(entry.getKey()));
                }
            }
        }
        if (!notExistingProductId.isEmpty()) {
            throw new ServiceException("Not available product id: " + notExistingProductId);
        }
        return productList;
    }

    public List<Product> checkExistingProductQuantity(List<Product> products, Map<String, Integer> inputCriteria) throws ServiceException {
        List<Product> productList = new ArrayList<>();
        List<Product> notExistingProductQuantity = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getQuantity() >= inputCriteria.get(products.get(i).getId().toString())) {
                productList.add(products.get(i));
                productList.get(i).setQuantity(inputCriteria.get(products.get(i).getId().toString()));
            } else {
                notExistingProductQuantity.add(products.get(i));
            }
        }
        if (!notExistingProductQuantity.isEmpty()) {
            throw new ServiceException("Not available quantity for products: " + notExistingProductQuantity);
        }
        return productList;
    }
}

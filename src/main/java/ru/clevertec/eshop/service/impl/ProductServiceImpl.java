package ru.clevertec.eshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.eshop.dao.DAOFactory;
import ru.clevertec.eshop.dao.DAOFactoryProvider;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.ProductDAO;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.ProductService;
import ru.clevertec.eshop.service.exception.ServiceException;
import ru.clevertec.eshop.service.validation.DataValidator;
import ru.clevertec.eshop.util.parsing.DataParser;

import java.util.*;

import static java.lang.Integer.valueOf;
import static java.lang.Long.parseLong;

@Service
@Transactional
public class ProductServiceImpl implements ProductService<Product> {
    private static final String CARD = "card";
    private DAOFactoryProvider factoryProvider = DAOFactory.getInstance();
    private ProductDAO productDAO = factoryProvider.getProductDAO();

    @Override
    public List<Product> findAll() {
        List<Product> products = null;
        try {
            products = productDAO.findAll();
        } catch (DAOException e) {
            //LOGGER.error(e);
        }
        return products;
    }

    @Override
    public Optional<Product> findByID(Long entityId) {
        Optional<Product> product = Optional.empty();
        try {
            product = productDAO.findByID(entityId);
        } catch (DAOException e) {
            //LOGGER.error(e);
        }
        return product;
    }

    @Override
    public List<Product> obtainValidatedProducts(String[] args) throws ServiceException {
        Map<String, Integer> map = obtainValidatedCriteria(args);
        List<Product> correctProductsId = checkExistingProductId(map);
        List<Product> correctProductsQuantityAndId = checkExistingProductQuantity(correctProductsId, map);
        return correctProductsQuantityAndId;
    }

    private Map<String, Integer> obtainValidatedCriteria(String[] args) throws ServiceException {
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

    private List<Product> checkExistingProductId(Map<String, Integer> inputCriteria) throws ServiceException {
        List<Product> productList = new ArrayList<>();
        List<Long> notExistingProductId = new ArrayList<>();
        Optional<Product> product = Optional.empty();
        for (Map.Entry<String, Integer> entry : inputCriteria.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(CARD)) {
                try {
                    product = productDAO.findByID(parseLong(entry.getKey()));
                } catch (DAOException e) {
                    //LOGGER.error(e);
                    notExistingProductId.add(parseLong(entry.getKey()));
                }
                productList.add(product.get());
            }
        }
        if (!notExistingProductId.isEmpty()) {
            throw new ServiceException("Not available product id: " + notExistingProductId);
        }
        return productList;
    }

    private List<Product> checkExistingProductQuantity(List<Product> products, Map<String, Integer> inputCriteria) throws ServiceException {
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

package ru.clevertec.eshop.dao.impl;

import ru.clevertec.eshop.dao.ProductDAO;
import ru.clevertec.eshop.model.Product;
import ru.clevertec.eshop.model.discount.AbstractDiscount;
import ru.clevertec.eshop.model.discount.DiscountCard;
import ru.clevertec.eshop.model.discount.DiscountType;
import ru.clevertec.eshop.model.discount.construction.DiscountCardConstructor;
import ru.clevertec.eshop.model.discount.construction.DiscountPromoConstructor;
import ru.clevertec.eshop.model.discount.construction.SearchCriteria;
import ru.clevertec.eshop.util.AppConstant;

import java.io.*;
import java.util.*;

public class ProductDAOFromFile implements ProductDAO {
    private static final String DISCOUNTS_FILE = AppConstant.DISCOUNTS_FILE;
    private static final String CARDS_FILE = AppConstant.CARDS_FILE;
    private static final String PRODUCTS_FILE = AppConstant.PRODUCTS_FILE;
    private final List<AbstractDiscount> discountsList = new ArrayList<>();
    private final List<DiscountCard> cardsList = new ArrayList<>();

    @Override
    public List<Product> find(Long id) {
        return null;
    }

    public List<AbstractDiscount> obtainDiscountsData() {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(DISCOUNTS_FILE))
                .getPath();
        Map<String, Object> discountMap;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                discountMap = obtainMap(line);
                discountsList.add(createDiscount(discountMap));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to discounts info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return discountsList;
    }

    public List<DiscountCard> obtainCardsData() {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(CARDS_FILE))
                .getPath();
        Map<String, Object> cardMap;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                cardMap = obtainMap(line);
                cardsList.add(createCard(cardMap));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to cards info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return cardsList;
    }

    private static final String SIGNS_TO_REPLACE = "(;|:|=|,|\\s)+";
    private static final String NEW_DELIMETER = " ";

    private String[] parseLine(String line) {
        String[] parameters = line.replaceAll(SIGNS_TO_REPLACE, NEW_DELIMETER).split(NEW_DELIMETER);
        return parameters;
    }

    private Map<String, Object> obtainMap(String line) {
        String[] parameters = parseLine(line);
        Map<String, Object> paramsValues = new HashMap<>();
            for (int i = 1; i < parameters.length; i += 2) {
                paramsValues.put(parameters[i], parameters[i + 1]);
            }
        return paramsValues;
    }

    private AbstractDiscount createDiscount(Map<String, Object> map) {
        AbstractDiscount discount = null;
        String discountType = (String) map.get(SearchCriteria.Discount.TYPE.toString());
        switch (discountType) {
            case "DISCOUNT_CARD":
                discount = new DiscountCardConstructor().constructDiscount(map);
                break;
            case "SALES_PROMOTION":
                discount = new DiscountPromoConstructor().constructDiscount(map);
        }
        return discount;
    }

    private DiscountCard createCard(Map<String, Object> map) {
        return (DiscountCard) new DiscountCardConstructor().constructDiscount(map);
    }
}

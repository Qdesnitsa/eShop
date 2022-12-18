package ru.clevertec.eshop.dao.impl.file;

import ru.clevertec.eshop.dao.BaseDAO;
import ru.clevertec.eshop.model.promo.Promo;
import ru.clevertec.eshop.dao.impl.file.construction.PromoConstructor;
import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.util.AppConstant;

import java.io.*;
import java.util.*;

public class PromoDAOFromFile implements BaseDAO<Promo> {
    private final String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(AppConstant.PROMO_FILE))
            .getPath();
    @Override
    public List<Promo> findAll() {
        List<Promo> discountsList = new ArrayList<>();

        Map<String, Object> discountMap;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                discountMap = obtainMap(line);
                discountsList.add(createPromo(discountMap));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to promo info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return discountsList;
    }

    @Override
    public Optional<Promo> findByID(Long promoId) {
        Map<String, Object> promoMap;
        Promo promo = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                promoMap = obtainMap(line);
                if (promoMap.get(SearchCriteria.Card.ID) == promoId) {
                    promo = createPromo(promoMap);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to discounts info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return Optional.ofNullable(promo);
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

    private Promo createPromo(Map<String, Object> map) {
        Promo promo = new PromoConstructor().constructPromo(map);
        return promo;
    }
}

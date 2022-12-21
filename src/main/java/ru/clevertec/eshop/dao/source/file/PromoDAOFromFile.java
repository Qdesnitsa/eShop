package ru.clevertec.eshop.dao.source.file;

import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.PromoDAO;
import ru.clevertec.eshop.model.promo.Promo;
import ru.clevertec.eshop.dao.construction.PromoConstructor;
import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.util.AppConstant;
import ru.clevertec.eshop.util.parsing.DataParser;

import java.io.*;
import java.util.*;

import static java.lang.Long.parseLong;

public class PromoDAOFromFile implements PromoDAO<Promo> {
    private final String filePath = AppConstant.PROMO_FILE;

    @Override
    public List<Promo> findAll() throws DAOException {
        List<Promo> discountsList = new ArrayList<>();

        Map<String, Object> discountMap;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                discountMap = DataParser.obtainMap(line);
                discountsList.add(createPromo(discountMap));
            }
        } catch (FileNotFoundException e) {
            throw new DAOException("Check file path to promo info. File is not fount", e);
        } catch (IOException e) {
            throw new DAOException("Error in getting data from file", e);
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
                promoMap = DataParser.obtainMap(line);
                if (parseLong((String) promoMap.get(SearchCriteria.Card.ID.toString())) == promoId) {
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

    private Promo createPromo(Map<String, Object> map) {
        Promo promo = new PromoConstructor().constructEntity(map);
        return promo;
    }
}

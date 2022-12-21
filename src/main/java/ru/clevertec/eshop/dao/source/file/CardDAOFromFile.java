package ru.clevertec.eshop.dao.source.file;

import ru.clevertec.eshop.dao.source.CardDAO;
import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.dao.construction.CardConstructor;
import ru.clevertec.eshop.util.AppConstant;
import ru.clevertec.eshop.util.parsing.DataParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class CardDAOFromFile implements CardDAO<DiscountCard> {
    private final String filePath = AppConstant.CARDS_FILE;

    @Override
    public List<DiscountCard> findAll() {
        List<DiscountCard> cardsList = new ArrayList<>();

        Map<String, Object> cardMap;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                cardMap = DataParser.obtainMap(line);
                cardsList.add(createCard(cardMap));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to discounts info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return cardsList;
    }

    @Override
    public Optional<DiscountCard> findByID(Long cardId) {
        Map<String, Object> cardMap;
        DiscountCard discountCard = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                cardMap = DataParser.obtainMap(line);
                if (parseLong((String) cardMap.get(SearchCriteria.Card.ID.toString())) == cardId) {
                    discountCard = createCard(cardMap);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to cards info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return Optional.ofNullable(discountCard);
    }

    @Override
    public Optional<DiscountCard> findCardByNumber(int cardNumber) {
        Map<String, Object> cardMap;
        DiscountCard discountCard = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                cardMap = DataParser.obtainMap(line);
                if (parseInt((String) cardMap.get(SearchCriteria.Card.NUMBER.toString())) == cardNumber) {
                    discountCard = createCard(cardMap);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to cards info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return Optional.ofNullable(discountCard);
    }

    private DiscountCard createCard(Map<String, Object> map) {
        DiscountCard discountCard = new CardConstructor().constructEntity(map);
        return discountCard;
    }
}

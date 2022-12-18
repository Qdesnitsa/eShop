package ru.clevertec.eshop.dao.impl.file;

import ru.clevertec.eshop.dao.BaseDAO;
import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.dao.impl.file.construction.CardConstructor;
import ru.clevertec.eshop.util.AppConstant;
import ru.clevertec.eshop.util.parsing.DataParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CardDAOFromFile implements BaseDAO {
    private final String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(AppConstant.CARDS_FILE)).getPath();

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
                if (cardMap.get(SearchCriteria.Card.ID) == cardId) {
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

package ru.clevertec.eshop.service.impl;

import ru.clevertec.eshop.dao.impl.file.CardDAO;
import ru.clevertec.eshop.dao.impl.file.impl.CardDAOFromFile;
import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.service.CardService;
import ru.clevertec.eshop.service.exception.ServiceException;
import ru.clevertec.eshop.service.validation.DataValidator;
import ru.clevertec.eshop.util.parsing.DataParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Integer.valueOf;

public class CardServiceImpl implements CardService {

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Optional findByID(Long entityId) {
        return Optional.empty();
    }

    @Override
    public Optional<DiscountCard> findCardByNumber(int cardNumber) {
        return Optional.empty();
    }
    private static final String CARD = "card";
    CardDAO cardBaseDAO = new CardDAOFromFile();

    public Optional<DiscountCard> obtainValidatedCard(String[] args) throws ServiceException {
        Map<String, Integer> map = obtainValidatedCriteria(args);
        Optional<DiscountCard> discountCard = checkDiscountCardById(map);
        return discountCard;
    }

    public Optional<DiscountCard> checkDiscountCardById(Map<String, Integer> map) throws ServiceException {
        Optional<DiscountCard> discountCard = null;
        if (map.containsKey(CARD)) {
            discountCard = cardBaseDAO.findCardByNumber(Integer.parseInt(map.get(CARD).toString()));
            if (discountCard.isEmpty()) {
                throw new ServiceException("Provided card number " + map.get(CARD) + " does not exist in database");
            }
        }
        return discountCard;
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
}

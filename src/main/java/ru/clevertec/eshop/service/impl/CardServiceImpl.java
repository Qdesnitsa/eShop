package ru.clevertec.eshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.eshop.dao.DAOFactory;
import ru.clevertec.eshop.dao.DAOFactoryProvider;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.CardDAO;
import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.service.CardService;
import ru.clevertec.eshop.service.exception.ServiceException;
import ru.clevertec.eshop.service.validation.DataValidator;
import ru.clevertec.eshop.util.parsing.DataParser;

import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Integer.valueOf;

@Service
@Transactional
public class CardServiceImpl implements CardService<DiscountCard> {
    private DAOFactoryProvider factoryProvider = DAOFactory.getInstance();
    private CardDAO cardDAO = factoryProvider.getCardDAO();
    private static final String CARD = "card";

    @Override
    public List<DiscountCard> findAll() throws ServiceException {
        try {
            return cardDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Failed attempt to find all cards in the database", e);
        }
    }

    @Override
    public Optional<DiscountCard> findByID(Long entityId) throws ServiceException {
        try {
            return cardDAO.findByID(entityId);
        } catch (DAOException e) {
            throw new ServiceException("Failed attempt to find card by id in the database", e);
        }
    }

    @Override
    public Optional<DiscountCard> findCardByNumber(int cardNumber) throws ServiceException {
        try {
            return cardDAO.findCardByNumber(cardNumber);
        } catch (DAOException e) {
            throw new ServiceException("Failed attempt to find card by id in the database", e);
        }
    }

    @Override
    public Optional<DiscountCard> obtainValidatedCard(String[] args) throws ServiceException {
        Map<String, Integer> map = obtainValidatedCriteria(args);
        Optional<DiscountCard> discountCard = checkDiscountCardByNumber(map);
        return discountCard;
    }

    private Optional<DiscountCard> checkDiscountCardByNumber(Map<String, Integer> map) throws ServiceException {
        Optional<DiscountCard> discountCard = null;
        if (map.containsKey(CARD)) {
            try {
                discountCard = cardDAO.findCardByNumber(Integer.parseInt(map.get(CARD).toString()));
            } catch (DAOException e) {
                throw new ServiceException("Provided card number " + map.get(CARD) + " does not exist in database");
            }
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

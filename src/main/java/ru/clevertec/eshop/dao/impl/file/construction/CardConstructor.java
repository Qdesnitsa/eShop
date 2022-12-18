package ru.clevertec.eshop.dao.impl.file.construction;

import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.card.CardLevel;
import ru.clevertec.eshop.model.card.DiscountCard;

import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class CardConstructor implements EntityConstructor<DiscountCard> {
    private static DiscountCard discountCard;

    @Override
    public DiscountCard constructEntity(Map<String, Object> map) {
        return discountCard = DiscountCard.newBuilder()
                .setId(parseLong((String) map.get(SearchCriteria.Card.ID.toString())))
                .setCardNumber(parseInt((String) map.get(SearchCriteria.Card.NUMBER.toString())))
                .setCardLevel(CardLevel.valueOf((String) map.get(SearchCriteria.Card.LEVEL.toString())))
                .build();
    }
}

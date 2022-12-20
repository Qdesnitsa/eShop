package ru.clevertec.eshop.dao.construction;

import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.card.CardLevel;
import ru.clevertec.eshop.model.card.DiscountCard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class CardConstructor implements EntityConstructor<DiscountCard> {

    @Override
    public DiscountCard constructEntity(Map<String, Object> map) {
        return DiscountCard.newBuilder()
                .setId(parseLong((String) map.get(SearchCriteria.Card.ID.toString())))
                .setCardNumber(parseInt((String) map.get(SearchCriteria.Card.NUMBER.toString())))
                .setCardLevel(CardLevel.valueOf((String) map.get(SearchCriteria.Card.LEVEL.toString())))
                .build();
    }

    @Override
    public DiscountCard constructEntity(ResultSet resultSet) throws SQLException {
        return DiscountCard.newBuilder()
                .setId(resultSet.getLong("id"))
                .setCardNumber(resultSet.getInt("card_number"))
                .setCardLevel(CardLevel.valueOf(resultSet.getString("card_level")))
                .build();
    }
}

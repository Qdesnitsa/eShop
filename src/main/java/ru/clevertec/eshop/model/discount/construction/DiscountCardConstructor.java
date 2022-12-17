package ru.clevertec.eshop.model.discount.construction;

import ru.clevertec.eshop.model.discount.AbstractDiscount;
import ru.clevertec.eshop.model.discount.DiscountCard;

import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class DiscountCardConstructor implements DiscountConstructor {
    private static AbstractDiscount discountCard;
    @Override
    public AbstractDiscount constructDiscount(Map<String, Object> map) {
        return discountCard = DiscountCard.newBuilder()
                .setId(parseLong((String) map.get(SearchCriteria.Discount.ID.toString())))
                .setValue(parseDouble((String) map.get(SearchCriteria.Discount.VALUE.toString())))
                .build();
    }
}

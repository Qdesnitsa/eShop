package ru.clevertec.eshop.model.discount.construction;

import ru.clevertec.eshop.model.discount.AbstractDiscount;
import ru.clevertec.eshop.model.discount.DiscountCard;
import ru.clevertec.eshop.model.discount.DiscountPromo;

import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class DiscountPromoConstructor implements DiscountConstructor {
    private static AbstractDiscount discountPromo;
    @Override
    public AbstractDiscount constructDiscount(Map<String, Object> map) {
        return discountPromo = DiscountPromo.newBuilder()
                .setId(parseLong((String) map.get(SearchCriteria.Discount.ID.toString())))
                .setValue(parseDouble((String) map.get(SearchCriteria.Discount.VALUE.toString())))
                .setName((String) map.get(SearchCriteria.Discount.NAME.toString()))
                .setProductsQuantity(parseInt((String) map.get(SearchCriteria.Discount.QUANTITY.toString())))
                .build();
    }
}

package ru.clevertec.eshop.dao.impl.file.construction;

import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.promo.Promo;

import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class PromoConstructor {
    private static Promo promo;

    public Promo constructPromo(Map<String, Object> map) {
        return promo = Promo.newBuilder()
                .setId(parseLong((String) map.get(SearchCriteria.Promo.ID.toString())))
                .setValue(parseDouble((String) map.get(SearchCriteria.Promo.VALUE.toString())))
                .setName((String) map.get(SearchCriteria.Promo.NAME.toString()))
                .setProductsQuantity(parseInt((String) map.get(SearchCriteria.Promo.QUANTITY.toString())))
                .build();
    }
}

package ru.clevertec.eshop.dao.construction;

import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.promo.Promo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class PromoConstructor implements EntityConstructor<Promo> {

    public Promo constructEntity(Map<String, Object> map) {
        return new Promo.Builder()
                .setId(parseLong((String) map.get(SearchCriteria.Promo.ID.toString())))
                .setValue(parseDouble((String) map.get(SearchCriteria.Promo.VALUE.toString())))
                .setName((String) map.get(SearchCriteria.Promo.NAME.toString()))
                .setProductsQuantity(parseInt((String) map.get(SearchCriteria.Promo.QUANTITY.toString())))
                .build();
    }

    @Override
    public Promo constructEntity(ResultSet resultSet) throws SQLException {
        return new Promo.Builder()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("promo_name"))
                .setValue(resultSet.getDouble("promo_value"))
                .setProductsQuantity(resultSet.getInt("products_quantity"))
                .build();
    }
}

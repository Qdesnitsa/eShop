package ru.clevertec.eshop.dao.impl.file.construction;

import ru.clevertec.eshop.dao.impl.file.PromoDAO;
import ru.clevertec.eshop.dao.impl.file.impl.PromoDAOFromFile;
import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.promo.Promo;
import ru.clevertec.eshop.model.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class ProductConstructor implements EntityConstructor<Product> {
    private PromoDAO productDAO = new PromoDAOFromFile();
    private List<Promo> promoList = productDAO.findAll();

    @Override
    public Product constructEntity(Map<String, Object> map) {
        return Product.builder()
                .id(parseLong((String) map.get(SearchCriteria.Product.ID.toString())))
                .name((String) map.get(SearchCriteria.Product.NAME.toString()))
                .discount(promoList.get(parseInt((String) map.get(SearchCriteria.Product.DISCOUNT_ID.toString()))))
                .price(new BigDecimal((String) map.get(SearchCriteria.Product.PRICE.toString())))
                .quantityAvailable(parseInt((String) map.get(SearchCriteria.Product.QUANTITY.toString())))
                .build();
    }
}

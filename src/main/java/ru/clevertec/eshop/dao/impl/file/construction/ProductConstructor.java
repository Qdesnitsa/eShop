package ru.clevertec.eshop.dao.impl.file.construction;

import ru.clevertec.eshop.dao.BaseDAO;
import ru.clevertec.eshop.dao.impl.file.PromoDAOFromFile;
import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.promo.Promo;
import ru.clevertec.eshop.model.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

public class ProductConstructor implements EntityConstructor<Product> {
    private Product product;
    private BaseDAO productDAO = new PromoDAOFromFile();
    private List<Promo> promoList = productDAO.findAll();
    @Override
    public Product constructEntity(Map<String, Object> map) {
        return product = Product.builder()
                .id(parseLong((String) map.get(SearchCriteria.Product.ID.toString())))
                .name((String) map.get(SearchCriteria.Product.NAME.toString()))
                .discount(promoList.get((Integer) map.get(SearchCriteria.Product.DISCOUNT_ID)))
                .price((BigDecimal) map.get(SearchCriteria.Product.PRICE.toString()))
                .quantityAvailable((Integer) map.get(SearchCriteria.Product.PRICE.toString()))
                .build();
    }
}

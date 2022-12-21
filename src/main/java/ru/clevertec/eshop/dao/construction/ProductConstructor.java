package ru.clevertec.eshop.dao.construction;

import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.PromoDAO;
import ru.clevertec.eshop.dao.source.file.PromoDAOFromFile;
import ru.clevertec.eshop.model.SearchCriteria;
import ru.clevertec.eshop.model.promo.Promo;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.ProductService;
import ru.clevertec.eshop.service.PromoService;
import ru.clevertec.eshop.service.ServiceFactory;
import ru.clevertec.eshop.service.ServiceFactoryProvider;
import ru.clevertec.eshop.service.exception.ServiceException;
import ru.clevertec.eshop.service.impl.ProductServiceImpl;
import ru.clevertec.eshop.service.impl.PromoServiceImpl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class ProductConstructor implements EntityConstructor<Product> {

    @Override
    public Product constructEntity(Map<String, Object> map) {
        PromoService promoService = new PromoServiceImpl();
        List<Promo> promoList = null;
        try {
            promoList = promoService.findAll();
        } catch (ServiceException e) {
            //LOGGER.error(e);
        }
        return Product.builder()
                .id(parseLong((String) map.get(SearchCriteria.Product.ID.toString())))
                .name((String) map.get(SearchCriteria.Product.NAME.toString()))
                .discount(promoList
                        .stream()
                        .filter(s -> (s.getId()
                                .equals(Long.valueOf((String) map.get(SearchCriteria.Product.DISCOUNT_ID.toString())))))
                        .findFirst().get())
                .price(new BigDecimal((String) map.get(SearchCriteria.Product.PRICE.toString())))
                .quantityAvailable(parseInt((String) map.get(SearchCriteria.Product.QUANTITY.toString())))
                .build();
    }

    @Override
    public Product constructEntity(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getLong("product_id"))
                .name(resultSet.getString("name"))
                .discount(new Promo.Builder()
                        .setId(Long.valueOf(resultSet.getString("promo_id")))
                        .setName(resultSet.getString("promo_name"))
                        .setValue(Double.parseDouble(resultSet.getString("promo_value")))
                        .setProductsQuantity(Integer.parseInt(resultSet.getString("products_quantity")))
                        .build())
                .price(new BigDecimal(resultSet.getString("price")))
                .quantityAvailable(Integer.parseInt(resultSet.getString("quantity")))
                .build();
    }
}

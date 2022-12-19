package ru.clevertec.eshop.controller.console;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.impl.database.ConnectionFactory;
import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.*;
import ru.clevertec.eshop.service.exception.ServiceException;

public class InputDataProcessor {
    ServiceFactoryProvider serviceFactory = ServiceFactory.getInstance();
    CheckService checkService= serviceFactory.getCheckService();

    ProductService productService = serviceFactory.getProductService();
    CardService cardService = serviceFactory.getCardService();

    public String obtainCheck(String[] args) throws ServiceException, IOException {
        List<Product> products = productService.obtainValidatedProducts(args);
        Optional<DiscountCard> discountCard = cardService.obtainValidatedCard(args);
        String check = checkService.obtainCheck(products, discountCard);
        checkService.save(check);
        System.out.println(check);
        try(Connection con = ConnectionFactory.getConnection()) {
            System.out.println("Successful");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return check;
    }
}

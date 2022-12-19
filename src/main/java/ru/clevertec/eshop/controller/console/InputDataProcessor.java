package ru.clevertec.eshop.controller.console;

import java.util.List;
import java.util.Optional;

import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.exception.ServiceException;
import ru.clevertec.eshop.service.impl.CardServiceImpl;
import ru.clevertec.eshop.service.impl.CheckServiceImpl;
import ru.clevertec.eshop.service.impl.ProductServiceImpl;

public class InputDataProcessor {
    ProductServiceImpl productService = new ProductServiceImpl();
    CardServiceImpl cardService = new CardServiceImpl();
    CheckServiceImpl checkService = new CheckServiceImpl();

    public String obtainCheck(String[] args) throws ServiceException {
        List<Product> products = productService.obtainValidatedProducts(args);
        Optional<DiscountCard> discountCard = cardService.obtainValidatedCard(args);
        String check = checkService.obtainCheck(products, discountCard);
        System.out.println(check);
        return check;
    }
}

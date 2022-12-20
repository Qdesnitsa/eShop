package ru.clevertec.eshop.controller.console;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import ru.clevertec.eshop.dto.CheckDTO;
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
        Optional<CheckDTO> check = checkService.obtainCheckDTO(products, discountCard);
        checkService.save(check);
        return check.get().toString();
    }
}

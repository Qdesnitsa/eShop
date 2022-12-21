package ru.clevertec.eshop.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.eshop.dto.CheckDTO;
import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.CardService;
import ru.clevertec.eshop.service.CheckService;
import ru.clevertec.eshop.service.ProductService;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/checks")
public class CheckController {
    ProductService productService;
    CheckService checkService;
    CardService cardService;

    public CheckController(@Qualifier("productServiceImpl") ProductService productService,
    @Qualifier("checkServiceImpl") CheckService checkService,
    @Qualifier("cardServiceImpl") CardService cardService) {
        this.productService = productService;
        this.checkService = checkService;
        this.cardService = cardService;
    }

    @GetMapping()
    public String saveAndGetCheck(@RequestParam String[] args) throws ServiceException, IOException {
        List<Product> products = productService.obtainValidatedProducts(args);
        Optional<DiscountCard> discountCard = cardService.obtainValidatedCard(args);
        Optional<CheckDTO> check = (Optional<CheckDTO>) checkService.obtainCheckDTO(products, discountCard);
        checkService.save(check);
        String formattedCheck = checkService.obtainFormattedHTMLCheck(check);
        return formattedCheck;
    }

}

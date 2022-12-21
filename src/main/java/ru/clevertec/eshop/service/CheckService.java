package ru.clevertec.eshop.service;

import ru.clevertec.eshop.dto.CheckDTO;
import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CheckService<T> {
    boolean save(T checkDTO) throws IOException, ServiceException;
    T obtainCheckDTO(List<Product> products, Optional<DiscountCard> card);
    String obtainFormattedFileCheck(T checkDTO);
    String obtainFormattedHTMLCheck(T checkDTO);
}

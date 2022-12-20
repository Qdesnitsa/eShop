package ru.clevertec.eshop.service.impl;

import ru.clevertec.eshop.dao.DAOFactory;
import ru.clevertec.eshop.dao.DAOFactoryProvider;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.CheckDAO;
import ru.clevertec.eshop.dto.CheckDTO;
import ru.clevertec.eshop.model.Check;
import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.CheckService;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class CheckServiceImpl implements CheckService {
    private static final String DATE_AND_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final AtomicLong idGenerator = new AtomicLong(0);
    private DAOFactoryProvider factoryProvider = DAOFactory.getInstance();
    private CheckDAO checkDAO = factoryProvider.getCheckDAO();

    @Override
    public boolean save(Optional<CheckDTO> checkDTO) throws IOException, ServiceException {
        boolean isCheckSaved = false;
        try {
            if (checkDAO.save(checkDTO)) {
                isCheckSaved = true;
            }
        } catch (DAOException e) {
            throw new ServiceException("Failed attempt to save check to the database", e);
        }
        return isCheckSaved;
    }

    public double obtainDiscountFromCard(Optional<DiscountCard> card) {
        double discount = card.isPresent() ? card.get().getCardLevel().getDiscount() : 0.0;
        return discount;
    }

    public BigDecimal obtainSumAfterPromoDiscount(Product product) {
        BigDecimal discount = BigDecimal.valueOf(product.getDiscount().getValue());
        BigDecimal quantityOfIdenticalProduct = BigDecimal.valueOf(product.getQuantity());
        BigDecimal productPrice = product.getPrice();
        BigDecimal paymentForAllProductId;
        BigDecimal paymentForSameProductId;
        if (!discount.equals(0.0) && product.getQuantity() > product.getDiscount().getProductsQuantity()) {
            paymentForAllProductId = productPrice.multiply(quantityOfIdenticalProduct);
            paymentForSameProductId = paymentForAllProductId.subtract(discount.multiply(paymentForAllProductId));
        } else {
            paymentForSameProductId = productPrice.multiply(quantityOfIdenticalProduct);
        }
        return paymentForSameProductId;
    }


    public Optional<CheckDTO> obtainCheckDTO(List<Product> products, Optional<DiscountCard> card) {
        Optional<CheckDTO> checkDTO = Optional.of(new CheckDTO());
        Check check = new Check();
        double discountFromCard = obtainDiscountFromCard(card);
        checkDTO.get().setDiscountFromCard(discountFromCard);
        BigDecimal checkSum = new BigDecimal(0).setScale(2, RoundingMode.UP);
        checkDTO.get().setId(idGenerator.incrementAndGet());
        checkDTO.get().setProducts(products);
        checkDTO.get().setShopId(check.getShopId());
        checkDTO.get().setDateTime(check.getDateTime());
        for (Product product : products) {
            checkSum = checkSum.add(obtainSumAfterPromoDiscount(product));
        }
        checkDTO.get().setCheckSumBeforeCard(checkSum);
        if (discountFromCard != 0) {
            BigDecimal totalAmountToPay = checkSum.subtract(checkSum.multiply(BigDecimal.valueOf(discountFromCard)));
            checkDTO.get().setTotalSum(totalAmountToPay);
        }
        return checkDTO;
    }

    @Override
    public String obtainFormattedCheck(Optional<CheckDTO> checkDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_AND_TIME_FORMAT);
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-12s%-10d", "CheckID: ", checkDTO.get().getId()))
                .append("\n")
                .append(String.format("%-12s%-10s", "Date&Time: ", checkDTO.get().getDateTime().format(formatter)))
                .append("\n")
                .append(String.format("%-12s%-10d", "ShopID", checkDTO.get().getShopId()))
                .append("\n")
                .append(String.format("%-10s%-10s%-10s%-10s%-10s\n", "Qty", "Product", "Price", "Discount%", "Total"));
        for (Product product : checkDTO.get().getProducts()) {
            builder.append(String.format("%-10d", product.getQuantity()))
                    .append(String.format("%-10s", product.getName()))
                    .append(String.format("%-10s", product.getPrice()))
                    .append(String.format("%-10s", (product.getDiscount().getValue() * 100)));

            builder.append(obtainSumAfterPromoDiscount(product))
                    .append("\n");
        }
        builder.append("------------------------------------\n")
                .append(String.format("%-40s", "Total payment: "))
                .append(checkDTO.get().getCheckSumBeforeCard())
                .append("\n");
        if (checkDTO.get().getDiscountFromCard() != 0) {
            builder.append(String.format("%-40s", "Discount % due to discount card: "))
                    .append(checkDTO.get().getDiscountFromCard() * 100)
                    .append("\n")
                    .append(String.format("%-40s", "Total payment after all discounts: "))
                    .append(checkDTO.get().getTotalSum().setScale(2, RoundingMode.UP));
        }
        return builder.toString();
    }
}

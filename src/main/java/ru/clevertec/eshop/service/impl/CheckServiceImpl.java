package ru.clevertec.eshop.service.impl;

import ru.clevertec.eshop.model.Check;
import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.model.product.Product;
import ru.clevertec.eshop.service.CheckService;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class CheckServiceImpl implements CheckService {
    private static final String DATE_AND_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Optional findByID(Long entityId) {
        return Optional.empty();
    }

    public double obtainDiscountFromCard(Optional<DiscountCard> card) {
        double discount = card.isPresent() ? card.get().getCardLevel().getDiscount() : 0.0;
        return discount;
    }

    public BigDecimal obtainDiscountFromPromo(Product product) {
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

    public String obtainCheck(List<Product> products, Optional<DiscountCard> card) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_AND_TIME_FORMAT);

        double discountFromCard = obtainDiscountFromCard(card);
        BigDecimal checkSum = new BigDecimal(0).setScale(2, RoundingMode.UP);
        Check check = new Check();
        check.setProducts(products);
        check.setId(idGenerator.incrementAndGet());
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-12s%-10d", "CheckID: ", check.getId()))
                .append("\n")
                .append(String.format("%-12s%-10s", "Date&Time: ", check.getDateTime().format(formatter)))
                .append("\n")
                .append(String.format("%-12s%-10d", "ShopID", check.getShopId()))
                .append("\n")
                .append(String.format("%-10s%-10s%-10s%-10s%-10s\n", "Qty", "Product", "Price", "Discount", "Total"));
        for (Product product : products) {
            builder.append(String.format("%-10d", product.getQuantity()))
                    .append(String.format("%-10s", product.getName()))

                    .append(String.format("%-10s", product.getPrice()))

                    .append(String.format("%-10s", product.getDiscount().getValue()));

            checkSum = checkSum.add(obtainDiscountFromPromo(product));
            builder.append(obtainDiscountFromPromo(product))
                    .append("\n");
        }
        builder.append("------------------------------------\n")
                .append(String.format("%-40s", "Total payment: "))
                .append(checkSum)
                .append("\n");
        if (discountFromCard != 0) {
            BigDecimal totalAmountToPay = checkSum.subtract(checkSum.multiply(BigDecimal.valueOf(discountFromCard)));
            builder.append(String.format("%-40s", "Discount sum due to discount card: "))
                    .append(discountFromCard)
                    .append("\n")
                    .append(String.format("%-40s", "Total payment after all discounts: "))
                    .append(totalAmountToPay.setScale(2, RoundingMode.UP));
        }
        return builder.toString();
    }
}

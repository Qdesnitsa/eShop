package ru.clevertec.eshop.model.discount;

import java.io.Serial;
import java.io.Serializable;

public class DiscountCard extends AbstractDiscount implements Serializable {

    @Serial
    private static final long serialVersionUID = -758423543028609594L;

    //    private User user;
    private int cardNumber;

    {
        discountType = DiscountType.DISCOUNT_CARD;
    }

    public DiscountCard() {
    }

    public DiscountCard(double value, DiscountType discountType, int cardNumber) {
        super(value, discountType);
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}

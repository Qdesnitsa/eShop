package ru.clevertec.eshop.model.discount;

public class DiscountCard extends AbstractDiscount {

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

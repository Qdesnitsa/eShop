package ru.clevertec.eshop.model.discount;

import java.io.Serial;
import java.io.Serializable;

public class DiscountCard extends AbstractDiscount implements Serializable {

    @Serial
    private static final long serialVersionUID = -758423543028609594L;

//    private User user;
//    private List<Check> checks;
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

    public static Builder newBuilder() {
        return new DiscountCard().new Builder();
    }

    public class Builder extends AbstractDiscount.Builder {

        private Builder() {
        }

        @Override
        public Builder setId(Long id) {
            super.setId(id);
            return this;
        }

        @Override
        public Builder setValue(double value) {
            super.setValue(value);
            return this;
        }

        @Override
        public Builder setDiscountType(DiscountType discountType) {
            super.setDiscountType(discountType);
            return this;
        }

        public Builder setCardNumber(int cardNumber) {
            DiscountCard.this.cardNumber = cardNumber;
            return this;
        }
    }
}

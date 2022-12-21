package ru.clevertec.eshop.model.card;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class DiscountCard implements Serializable {

    @Serial
    private static final long serialVersionUID = -758423543028609594L;
    private Long id;

//    private User user;
//    private List<Check> checks;
    private CardLevel cardLevel;
    private int cardNumber;


    public DiscountCard() {
    }

    public DiscountCard(Long id, CardLevel cardLevel, int cardNumber) {
        this.id = id;
        this.cardLevel = cardLevel;
        this.cardNumber = cardNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardLevel getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(CardLevel cardLevel) {
        this.cardLevel = cardLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return cardNumber == that.cardNumber && Objects.equals(id, that.id) && cardLevel == that.cardLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardLevel, cardNumber);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", cardLevel=" + cardLevel +
                ", cardNumber=" + cardNumber +
                "; ";
    }

    public static Builder newBuilder() {
        return new DiscountCard().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            DiscountCard.this.setId(id);
            return this;
        }

        public Builder setCardNumber(int cardNumber) {
            DiscountCard.this.cardNumber = cardNumber;
            return this;
        }

        public Builder setCardLevel(CardLevel cardLevel) {
            DiscountCard.this.cardLevel = cardLevel;
            return this;
        }

        public DiscountCard build() {
            return DiscountCard.this;
        }
    }
}

package ru.clevertec.eshop.model.discount;

import java.io.Serial;
import java.io.Serializable;

public class AbstractDiscount implements Serializable {
    @Serial
    private static final long serialVersionUID = 52944389383476338L;
    private Long id;
    private double value;
    protected DiscountType discountType;

    public AbstractDiscount() {
    }

    public AbstractDiscount(double value, DiscountType discountType) {
        this.value = value;
        this.discountType = discountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public static Builder newBuilder() {
        return new AbstractDiscount().new Builder();
    }

    public class Builder {

        protected Builder() {
        }

        public Builder setId(Long id) {
            AbstractDiscount.this.id = id;
            return this;
        }

        public Builder setValue(double value) {
            AbstractDiscount.this.value = value;
            return this;
        }

        public Builder setDiscountType(DiscountType discountType) {
            AbstractDiscount.this.discountType = discountType;
            return this;
        }

        public AbstractDiscount build() {
            return AbstractDiscount.this;
        }
    }
}

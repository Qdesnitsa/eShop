package ru.clevertec.eshop.model.discount;

import java.io.Serial;
import java.io.Serializable;

public class DiscountPromo extends AbstractDiscount implements Serializable {
    @Serial
    private static final long serialVersionUID = 4776441421394920796L;
    private String name;
    private int productsQuantity;

    {
        discountType = DiscountType.SALES_PROMOTION;
    }

    public DiscountPromo() {
    }

    public DiscountPromo(double value, DiscountType discountType, String name, int productsQuantity) {
        super(value, discountType);
        this.name = name;
        this.productsQuantity = productsQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(int productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    public static DiscountPromo.Builder newBuilder() {
        return new DiscountPromo().new Builder();
    }

    public class Builder extends AbstractDiscount.Builder {

        private Builder() {
        }

        @Override
        public DiscountPromo.Builder setId(Long id) {
            super.setId(id);
            return this;
        }

        @Override
        public DiscountPromo.Builder setValue(double value) {
            super.setValue(value);
            return this;
        }

        @Override
        public DiscountPromo.Builder setDiscountType(DiscountType discountType) {
            super.setDiscountType(discountType);
            return this;
        }

        public DiscountPromo.Builder setName(String name) {
            DiscountPromo.this.name = name;
            return this;
        }

        public DiscountPromo.Builder setProductsQuantity(int productsQuantity) {
            DiscountPromo.this.productsQuantity = productsQuantity;
            return this;
        }
    }
}

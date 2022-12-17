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
}

package ru.clevertec.eshop.model.discount;

import java.io.Serial;
import java.io.Serializable;

public abstract class AbstractDiscount implements Serializable {
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
}

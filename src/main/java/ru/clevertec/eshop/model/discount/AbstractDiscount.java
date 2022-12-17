package ru.clevertec.eshop.model.discount;

public abstract class AbstractDiscount {
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

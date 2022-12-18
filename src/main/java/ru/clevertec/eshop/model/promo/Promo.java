package ru.clevertec.eshop.model.promo;

import ru.clevertec.eshop.model.card.DiscountCard;

import java.io.Serial;
import java.io.Serializable;

public class Promo implements Serializable {
    @Serial
    private static final long serialVersionUID = 4776441421394920796L;
    private Long id;
    private String name;
    private double value;
    private int productsQuantity;

    public Promo() {
    }

    public Promo(Long id, String name, double value, int productsQuantity) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.productsQuantity = productsQuantity;
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

    public static Promo.Builder newBuilder() {
        return new Promo().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Promo.Builder setId(Long id) {
            Promo.this.setId(id);
            return this;
        }

        public Promo.Builder setValue(double value) {
            Promo.this.setValue(value);
            return this;
        }

        public Promo.Builder setName(String name) {
            Promo.this.name = name;
            return this;
        }

        public Promo.Builder setProductsQuantity(int productsQuantity) {
            Promo.this.productsQuantity = productsQuantity;
            return this;
        }
        public Promo build() {
            return Promo.this;
        }
    }
}

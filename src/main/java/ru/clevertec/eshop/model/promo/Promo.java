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

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", productsQuantity=" + productsQuantity +
                "; ";
    }

    public static class Builder {
        private final Promo newPromo;
        public Builder() {
            newPromo = new Promo();
        }

        public Builder setId(Long id) {
            newPromo.id = id;
            return this;
        }

        public Builder setValue(double value) {
            newPromo.value = value;
            return this;
        }

        public Builder setName(String name) {
            newPromo.name = name;
            return this;
        }

        public Promo.Builder setProductsQuantity(int productsQuantity) {
            newPromo.productsQuantity = productsQuantity;
            return this;
        }
        public Promo build() {
            return newPromo;
        }
    }
}

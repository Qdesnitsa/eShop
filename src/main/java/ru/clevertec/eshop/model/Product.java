package ru.clevertec.eshop.model;

import ru.clevertec.eshop.model.discount.AbstractDiscount;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1162347528072070883L;
    private Long id;
    private String name;
    private AbstractDiscount discount;
    private BigDecimal price;
    private int quantityAvailable;

    public Product() {
    }

    public Product(String name, AbstractDiscount discount, BigDecimal price, int quantityAvailable) {
        this.name = name;
        this.discount = discount;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(AbstractDiscount discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public static class ProductBuilder {
        private Long id;
        private String name;
        private AbstractDiscount discount;
        private BigDecimal price;
        private int quantityAvailable;

        public ProductBuilder() {
        }

        public ProductBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder discount(AbstractDiscount discount) {
            this.discount = discount;
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductBuilder quantityAvailable(int quantityAvailable) {
            this.quantityAvailable = quantityAvailable;
            return this;
        }
    }
}

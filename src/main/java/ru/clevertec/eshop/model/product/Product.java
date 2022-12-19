package ru.clevertec.eshop.model.product;

import ru.clevertec.eshop.model.promo.Promo;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1162347528072070883L;
    private Long id;
    private String name;
    private Promo discount;
    private BigDecimal price;
    private int quantity;

    public Product() {
    }

    public Product(Long id, String name, Promo discount, BigDecimal price, int quantityAvailable) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.price = price;
        this.quantity = quantityAvailable;
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

    public Promo getDiscount() {
        return discount;
    }

    public void setDiscount(Promo discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                ", quantityAvailable=" + quantity +
                "; ";
    }

    public static class ProductBuilder {
        private Long id;
        private String name;
        private Promo discount;
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

        public ProductBuilder discount(Promo discount) {
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

        public Product build() {
            return new Product(this.id, this.name, this.discount, this.price, this.quantityAvailable);
        }
    }
}

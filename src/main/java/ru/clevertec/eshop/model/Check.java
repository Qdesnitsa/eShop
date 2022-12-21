package ru.clevertec.eshop.model;

import ru.clevertec.eshop.model.product.Product;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Check implements Serializable {
    @Serial
    private static final long serialVersionUID = -9026753764235007910L;
    private Long id;
    private int shopId;
    private LocalDateTime dateTime;
    private List<Product> products;

    {
        shopId = 100;
        dateTime = LocalDateTime.now();
    }

    public Check() {
    }

    public Check(int shopId, LocalDateTime dateTime, List<Product> products) {
        this.shopId = shopId;
        this.dateTime = dateTime;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return shopId == check.shopId && Objects.equals(id, check.id) &&
                Objects.equals(dateTime, check.dateTime) && Objects.equals(products, check.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shopId, dateTime, products);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", shopId=" + shopId +
                ", dateTime=" + dateTime +
                ", products=" + products +
                "; ";
    }

    public static class Builder {
        private final Check newCheck;

        public Builder() {
            newCheck = new Check();
        }

        public Builder setId(Long id) {
            newCheck.id = id;
            return this;
        }

        public Builder setShopId(int shopId) {
            newCheck.shopId = shopId;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            newCheck.dateTime = dateTime;
            return this;
        }

        public Builder setProducts(List<Product> products) {
            newCheck.products = products;
            return this;
        }

        public Check build() {
            return newCheck;
        }
    }
}

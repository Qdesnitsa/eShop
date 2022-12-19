package ru.clevertec.eshop.model;

import ru.clevertec.eshop.model.product.Product;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
}

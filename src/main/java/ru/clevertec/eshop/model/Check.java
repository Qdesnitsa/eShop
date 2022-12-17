package ru.clevertec.eshop.model;

import java.time.LocalDateTime;
import java.util.List;

public class Check {
    private Long id;
    private String shopId;
    private String cashierId;
    private LocalDateTime dateTime;
    private List<Product> products;

    public Check() {
    }

    public Check(String shopId, String cashierId, LocalDateTime dateTime, List<Product> products) {
        this.shopId = shopId;
        this.cashierId = cashierId;
        this.dateTime = dateTime;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
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

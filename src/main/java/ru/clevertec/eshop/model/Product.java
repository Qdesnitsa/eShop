package ru.clevertec.eshop.model;

import ru.clevertec.eshop.model.discount.AbstractDiscount;

import java.math.BigDecimal;

public class Product {
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
}

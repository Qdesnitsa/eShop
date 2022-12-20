package ru.clevertec.eshop.model;

public final class SearchCriteria {
    public enum Promo {
        ID, NAME, VALUE, QUANTITY, TYPE
    }
    public enum Card {
        ID, USER_ID, NUMBER, LEVEL, TYPE
    }
    public enum Product {
        ID, NAME, DISCOUNT_ID, PRICE, QUANTITY, TYPE
    }
}

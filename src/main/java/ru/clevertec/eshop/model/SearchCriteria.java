package ru.clevertec.eshop.model;

public final class SearchCriteria {
    public static enum Promo {
        ID, NAME, VALUE, QUANTITY, TYPE
    }
    public static enum Card {
        ID, USER_ID, NUMBER, LEVEL, TYPE
    }
    public static enum Product {
        ID, NAME, DISCOUNT_ID, PRICE, QUANTITY, TYPE
    }
}

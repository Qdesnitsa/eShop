package ru.clevertec.eshop.model.card;

import ru.clevertec.eshop.util.AppConstant;

public enum CardLevel {
    DEFAULT(AppConstant.DEFAULT_CARD_DISCOUNT),
    CLASSIC(AppConstant.CLASSIC_DISCOUNT),
    SILVER(AppConstant.SILVER_DISCOUNT),
    GOLD(AppConstant.GOLD_DISCOUNT);
    private double discount;

    CardLevel(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}


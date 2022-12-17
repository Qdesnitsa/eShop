package ru.clevertec.eshop.model.discount.construction;

import ru.clevertec.eshop.model.discount.AbstractDiscount;

import java.util.Map;

public interface DiscountConstructor {
    AbstractDiscount constructDiscount(Map<String, Object> map);
}

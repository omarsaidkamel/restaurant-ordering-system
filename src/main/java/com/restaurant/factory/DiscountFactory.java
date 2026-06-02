package com.restaurant.factory;


import com.restaurant.discount.DiscountStrategy;
import com.restaurant.discount.FixedDiscount;
import com.restaurant.discount.NoDiscount;
import com.restaurant.discount.PercentageDiscount;

public class DiscountFactory {

    public DiscountStrategy createDiscountStrategy(String type,double value) {

        if (type == null) {
            throw new IllegalArgumentException("Discount type cannot be null");
        }

        switch (type.toLowerCase()) {
            case "none":
                return new NoDiscount();

            case "fixed":
                return new FixedDiscount(value);

            case "percentage":
                return new PercentageDiscount(value);

            default:
                throw new IllegalArgumentException("Unknown discount type: " + type);
        }
    }
}

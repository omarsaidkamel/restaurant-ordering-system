package com.restaurant.discount;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount; // No discount applied
    }
}

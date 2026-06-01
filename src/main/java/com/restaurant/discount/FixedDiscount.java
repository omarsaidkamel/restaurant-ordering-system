package com.restaurant.discount;

public class FixedDiscount implements DiscountStrategy{

    private double discountAmount;

    public FixedDiscount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        if (discountAmount > totalAmount) {
            return 0; // Ensure that the total amount does not go negative
        }
        return totalAmount - discountAmount;
    }
}

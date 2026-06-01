package com.restaurant.service;

import com.restaurant.discount.DiscountStrategy;
import com.restaurant.entity.Order;
import com.restaurant.exception.OrderNotPlacedException;
import com.restaurant.payment.PaymentMethod;

public class PaymentService {

    private Order order;

    public PaymentService(Order order) {
        this.order = order;
    }

    public void payOrder(PaymentMethod paymentMethod, DiscountStrategy discountStrategy) throws OrderNotPlacedException {
        if (!order.isPlaced()) {
            throw new OrderNotPlacedException("Error: Order must be placed before payment.");
        }

        if (order.isPaid()) {
            System.out.println("Payment failed. Order is already paid.");
            return;
        }

        double originalAmount = order.calculateTotalPrice();
        double finalAmount = discountStrategy.applyDiscount(originalAmount);

        System.out.println("Original Amount: " + originalAmount);
        System.out.println("Final Amount After Discount: " + finalAmount);

        paymentMethod.pay(finalAmount);

        order.setPaid(true);
        System.out.println("Payment completed successfully");
    }
}
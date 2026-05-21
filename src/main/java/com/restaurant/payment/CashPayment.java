package com.restaurant.payment;

public class CashPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Processing cash payment of $" + amount);
        // Here you would add logic to handle cash payment, such as giving change
    }
}

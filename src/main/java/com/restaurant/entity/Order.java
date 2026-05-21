package com.restaurant.entity;

import com.restaurant.exception.OrderNotPlacedException;
import com.restaurant.exception.OutOfStockException;
import com.restaurant.payment.PaymentMethod;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Integer id;
    private User user;
    private List<OrderItem> items;
    private boolean placed;
    private boolean paid;

    public Order(Integer id, User user) {
        this.id = id;
        this.user = user;
        this.items = new ArrayList<>();
        this.placed = false;
        this.paid = false;
    }

    public void addItem(Product product, int quantity) {
        if (placed) {
            System.out.println("Cannot add item. Order is already placed.");
            return;
        }

        OrderItem item = new OrderItem(product, quantity);
        items.add(item);
        System.out.println("Item added: " + product.getName());
    }

    public double calculateTotalPrice() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.calculateItemTotal();
        }

        return total;
    }

    public void placeOrder() throws OutOfStockException {
        if (items.isEmpty()) {
            System.out.println("Order failed. No items found.");
            placed = false;
            return;
        }

        for (OrderItem item : items) {
            if (!item.isAvailable()) {
                placed = false;
                throw new OutOfStockException("Error: Not enough stock for product: " + item.getProduct().getName());
            }
        }

        for (OrderItem item : items) {
            item.reduceProductStock();
        }

        placed = true;
        System.out.println("Order placed successfully");
    }

    public void payOrder(PaymentMethod paymentMethod) throws OrderNotPlacedException {
        if (!placed) {
            throw new OrderNotPlacedException("Error: Order must be placed before payment.");
        }

        if (paid) {
            System.out.println("Payment failed. Order is already paid.");
            return;
        }

        paymentMethod.pay(calculateTotalPrice());
        paid = true;
        System.out.println("Payment completed successfully");
    }

    public void displayOrderInfo() {
        System.out.println("Order ID: " + id);
        user.display();

        System.out.println("Order Items:");
        for (OrderItem item : items) {
            item.display();
            System.out.println("---");
        }

        System.out.println("Total Price: " + calculateTotalPrice());
        System.out.println("Placed: " + placed);
        System.out.println("Paid: " + paid);
    }
}
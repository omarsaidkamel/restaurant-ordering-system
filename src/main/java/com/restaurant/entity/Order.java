package com.restaurant.entity;

import java.util.ArrayList;
import java.util.List;

public class Order implements Identifiable{
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

    public List<OrderItem> getItems() {
        return items;
    }
    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
    public boolean isPlaced() {
        return placed;
    }
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    public boolean isPaid() {
        return paid;
    }

    @Override
    public Integer getId() {return id;}
}
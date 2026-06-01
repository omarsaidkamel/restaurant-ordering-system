package com.restaurant.service;

import com.restaurant.entity.Order;
import com.restaurant.entity.OrderItem;
import com.restaurant.exception.OutOfStockException;

public class OrderService {
    Order order;
    public OrderService(Order order){
        this.order = order;
    }
    public void placeOrder() throws OutOfStockException {
        if (order.getItems().isEmpty()) {
            System.out.println("Order failed. No items found.");
            order.setPlaced(false);
            return;
        }

        for (OrderItem item : order.getItems()) {
            if (!item.isAvailable()) {
                order.setPlaced(false);
                throw new OutOfStockException("Error: Not enough stock for product: " + item.getProduct().getName());
            }
        }

        for (OrderItem item : order.getItems()) {
            item.reduceProductStock();
        }

        order.setPlaced(true);
        System.out.println("Order placed successfully");
    }
}

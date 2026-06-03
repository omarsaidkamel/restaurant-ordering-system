package com.restaurant.builder;

import com.restaurant.entity.Order;
import com.restaurant.entity.Product;
import com.restaurant.entity.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderBuilder {

    private Integer id;
    private User user;
    private Map<Product, Integer> products = new LinkedHashMap<>();

    public OrderBuilder setId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Order id must be greater than zero");
        }
        this.id = id;
        return this;
    }

    public OrderBuilder setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.user = user;
        return this;
    }

    public OrderBuilder addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        products.put(product, quantity);
        return this;
    }

    public Order build() {
        if (id == null) {
            throw new IllegalArgumentException("Order id is required");
        }

        if (user == null) {
            throw new IllegalArgumentException("User is required");
        }

        if (products.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one product");
        }

        Order order = new Order(id, user);

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            order.addItem(entry.getKey(), entry.getValue());
        }

        return order;
    }
}
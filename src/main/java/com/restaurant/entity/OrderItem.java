package com.restaurant.entity;

import com.restaurant.exception.InvalidQuantityException;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        setQuantity(quantity);
    }

    public double calculateItemTotal() {
        return product.getPrice() * quantity;
    }

    public boolean isAvailable() {
        return product.isAvailable(quantity);
    }

    public void reduceProductStock() {
        if(quantity > 0) {
            product.reduceStock(quantity);
        }
        else{
            throw new InvalidQuantityException("Error: Quantity must be greater than zero");
        }
    }

    public void display() {
        System.out.println("Product: " + product.getName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Item Total: " + calculateItemTotal());
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
           throw new InvalidQuantityException("Error: Quantity must be greater than zero");
        }
    }
}
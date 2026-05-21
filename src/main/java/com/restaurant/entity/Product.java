package com.restaurant.entity;

public class Product {
    private Integer id;
    private String name;
    private double price;
    private Integer stock;


    public Product(Integer id, String name, double price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public Boolean isAvailable(Integer quantity) {
        return this.stock >= quantity;
    }
    public void reduceStock(int quantity) {
        if (isAvailable(quantity)) {
            stock = stock - quantity;
        } else {
            System.out.println("Not enough stock for product: " + name);
        }
    }

    public void display () {
        System.out.println("Product ID: " + id);
        System.out.println("Name: " + this.name);
        System.out.println("Price: " + this.price);
        System.out.println("Stock: " + this.stock);
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("Invalid price");
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            System.out.println("Invalid stock");
        }
    }

}

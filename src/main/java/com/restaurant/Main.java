package com.restaurant;

import com.restaurant.entity.Order;
import com.restaurant.entity.Product;
import com.restaurant.entity.User;
import com.restaurant.repository.Repository;

public class Main {
    public static void main(String[] args) {

        User user1 = new User(1, "Sara", "sara@example.com");
        user1.addLoyaltyPoints(50);

        Product pizza = new Product(2, "Pizza", 180.0, 5);
        Product cola = new Product(3, "Cola", 25.0, 20);
        Product burger = new Product(4, "Burger", 120.0, 15);
        Product cheese = new Product(5, "Cheese", 12.0, 15);

        Order order1 = new Order(1, user1);
        Order order2 = new Order(2, user1);
        Order order3 = new Order(3, user1);

        order1.addItem(pizza, 2);
        order1.addItem(cola, 3);

        order2.addItem(burger, 4);
        order2.addItem(cheese, 7);

        order3.addItem(cheese, 1);
        order3.addItem(pizza, 2);

        Repository<Product> productRepository = new Repository<>(Product.class);

        productRepository.add(pizza);
        productRepository.add(cola);
        productRepository.add(burger);
        productRepository.add(cheese);

        productRepository.findById(2)
                .ifPresentOrElse(
                        product -> System.out.println("Product found: " + product.getName()),
                        () -> System.out.println("Product not found")
                );

        System.out.println("Product with id 10 exists: " + productRepository.existsById(10));

        boolean deleted = productRepository.deleteById(3);

        if (deleted) {
            System.out.println("Product deleted successfully");
        } else {
            System.out.println("Product not found for delete");
        }

        productRepository.count();
    }
}
package com.restaurant;

import com.restaurant.entity.Order;
import com.restaurant.entity.Product;
import com.restaurant.entity.User;
import com.restaurant.exception.OrderNotPlacedException;
import com.restaurant.exception.OutOfStockException;
import com.restaurant.payment.PaymentMethod;
import com.restaurant.payment.WalletPayment;
import com.restaurant.repository.Repository;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        User user1 = new User(1, "Sara", "sara@example.com");
        user1.addLoyaltyPoints(50);

        Product pizza = new Product(2, "Pizza", 180.0, 5);
        Product cola = new Product(3, "Cola", 25.0, 20);
        Product burger = new Product(4, "Burger", 120.0, 15);
        Product cheese = new Product(5, "Cheese", 12.0, 15);


        Order order1 = new Order(1, user1);

        order1.addItem(pizza, 10);
        order1.addItem(cola, 3);

        try{
            order1.placeOrder();
        } catch (OutOfStockException e) {
            System.out.println(e.getMessage());
        }

        PaymentMethod walletPayment = new WalletPayment("Vodafone Cash");

         try{
             order1.payOrder(walletPayment);
         } catch (OrderNotPlacedException e) {
            System.out.println(e.getMessage());
         }

        System.out.println("----------------");

        order1.displayOrderInfo();

        System.out.println("----------------");

        pizza.display();
        cola.display();

        Repository<Product> productRepository = new Repository<>(Product.class);
        Repository<User> userRepository = new Repository<>(User.class);

        productRepository.add(pizza);
        productRepository.add(cola);
        userRepository.add(user1);

        productRepository.count();
        userRepository.count();

        Repository<Product> products = new Repository<>(Product.class);
        products.add(pizza);
        products.add(cola);
        products.add(burger);
        products.add(cheese);
        System.out.println("Products price greater than 100:");

        products.findAll()
                .stream()
                .filter(product -> product.getPrice() > 70)
                .forEach(product -> System.out.println(product.getName()));

        double totalPrices = products.findAll()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Total products prices: " + totalPrices);


        System.out.print("Product found: ");
        products.findAll()
                .stream()
                .filter(product -> product.getName() == "Pizza")
                .forEach(product -> System.out.println(product.getName()));

        System.out.println("Products sorted by price: ");
        products.findAll()
                .forEach(product -> System.out.println(product.getName()+" - "+product.getPrice()));
    }
}
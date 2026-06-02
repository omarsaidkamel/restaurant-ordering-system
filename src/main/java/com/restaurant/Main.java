package com.restaurant;

import com.restaurant.discount.DiscountStrategy;
import com.restaurant.discount.FixedDiscount;
import com.restaurant.discount.NoDiscount;
import com.restaurant.discount.PercentageDiscount;
import com.restaurant.entity.Order;
import com.restaurant.entity.Product;
import com.restaurant.entity.User;
import com.restaurant.exception.OrderNotPlacedException;
import com.restaurant.exception.OutOfStockException;
import com.restaurant.notification.AppNotification;
import com.restaurant.notification.EmailNotification;
import com.restaurant.notification.SMSNotification;
import com.restaurant.payment.PaymentMethod;
import com.restaurant.payment.WalletPayment;
import com.restaurant.repository.Repository;
import com.restaurant.service.OrderService;
import com.restaurant.service.PaymentService;

import java.util.Comparator;

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

        OrderService orderService = new OrderService(order1);
        PaymentService paymentService = new PaymentService(order1);

        System.out.println("Testing No Discount");
        PaymentMethod walletPayment = new WalletPayment("Vodafone Cash");
        DiscountStrategy discountStrategy = new NoDiscount();

        try {
            orderService.placeOrder();
            paymentService.payOrder(walletPayment, discountStrategy);
        } catch (OutOfStockException | OrderNotPlacedException e) {
            System.out.println(e.getMessage());
        }


        OrderService orderService2 = new OrderService(order2);
        PaymentService paymentService2 = new PaymentService(order2);

        System.out.println("Testing Percentage Discount");
        PaymentMethod walletPayment2 = new WalletPayment("Vodafone Cash");
        DiscountStrategy discountStrategy2 = new PercentageDiscount(10);

        try {
            orderService2.placeOrder();
            paymentService2.payOrder(walletPayment2, discountStrategy2);
        } catch (OutOfStockException | OrderNotPlacedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Testing Fixed Discount");
        OrderService orderService3 = new OrderService(order3);
        PaymentService paymentService3 = new PaymentService(order3);
        PaymentMethod walletPayment3 = new WalletPayment("Vodafone Cash");
        DiscountStrategy discountStrategy3 = new FixedDiscount(50);

        try {
            orderService3.placeOrder();
            paymentService3.payOrder(walletPayment3, discountStrategy3);
        } catch (OutOfStockException | OrderNotPlacedException e) {
            System.out.println(e.getMessage());
        }

        EmailNotification emailNotification = new EmailNotification();
        emailNotification.sendNotification("Order paid successfully");
        emailNotification.sendAttachment("Invoice.pdf");

        AppNotification appNotification = new AppNotification();
        appNotification.sendNotification("Order paid successfully");
        SMSNotification smsNotification = new SMSNotification();
        smsNotification.sendNotification("Order paid successfully");



        System.out.println("----------------");

        order1.displayOrderInfo();

        System.out.println("----------------");

        pizza.display();
        cola.display();

        System.out.println("----------------");

        Repository<Product> products = new Repository<>(Product.class);
        products.add(pizza);
        products.add(cola);
        products.add(burger);
        products.add(cheese);

        System.out.println("Products price greater than 100:");

        products.findAll()
                .stream()
                .filter(product -> product.getPrice() > 100)
                .forEach(product -> System.out.println(product.getName()));

        double totalPrices = products.findAll()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Total products prices: " + totalPrices);

        products.findAll()
                .stream()
                .filter(product -> product.getName().equalsIgnoreCase("Pizza"))
                .findFirst()
                .ifPresentOrElse(
                        product -> System.out.println("Product found: " + product.getName()),
                        () -> System.out.println("Product not found")
                );

        System.out.println("Products sorted by price:");

        products.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .forEach(product ->
                        System.out.println(product.getName() + " - " + product.getPrice())
                );
    }
}
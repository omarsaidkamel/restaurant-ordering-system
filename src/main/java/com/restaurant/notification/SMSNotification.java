package com.restaurant.notification;

public class SMSNotification implements NotificationSender {
    @Override
    public void sendNotification(String message) {
        // Simulate sending an SMS notification
        System.out.println("Sending SMS: " + message);
    }
}

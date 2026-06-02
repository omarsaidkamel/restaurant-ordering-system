package com.restaurant.notification;

public class AppNotification implements NotificationSender {

    @Override
    public void sendNotification(String message) {
        // Simulate sending an in-app notification
        System.out.println("Sending app notification: " + message);
    }

}

package com.restaurant.service;

import com.restaurant.notification.NotificationSender;
import java.util.List;

public class NotificationService {

    private final List<NotificationSender> notificationSenders;

    public NotificationService(List<NotificationSender> notificationSenders) {
        this.notificationSenders = notificationSenders;
    }

    public void sendToAll(String message) {
        for (NotificationSender sender : notificationSenders) {
            sender.sendNotification(message);
        }
    }
}
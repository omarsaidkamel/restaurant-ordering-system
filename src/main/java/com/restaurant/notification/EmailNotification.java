package com.restaurant.notification;

public class EmailNotification implements NotificationSender,  AttachmentSender {

    @Override
    public void sendNotification(String message) {
        // Simulate sending an email notification
        System.out.println("Sending email : " + message);
    }

    @Override
    public void sendAttachment(String fileName) {
        System.out.println("Sending email attachment: " + fileName);
    }
}

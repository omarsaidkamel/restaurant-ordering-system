package com.restaurant.entity;

public class User extends Person {
    private int loyaltyPoints;

    public User(Integer id, String name, String email) {
        super(id, name, email);
        this.loyaltyPoints = 0;
    }

    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            loyaltyPoints += points;
        }
    }

    @Override
    public void display() {
        displayBasicInfo();
        System.out.println("Loyalty Points: " + loyaltyPoints);
    }

}

package com.restaurant.entity;

public class Admin extends Person {
    private String role;

    public Admin(Integer id, String name, String email, String role) {
        super(id, name, email);
        this.role = role;
    }

    @Override
    public void display() {
        displayBasicInfo();
        System.out.println("Role: " + role);
    }
}
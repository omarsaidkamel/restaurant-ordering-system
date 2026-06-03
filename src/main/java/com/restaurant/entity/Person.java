package com.restaurant.entity;

public abstract class Person implements Identifiable{
    private Integer id;
    private String name;
    private String email;

    public Person(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public void displayBasicInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + this.name);
        System.out.println("Email: " + this.email);
    }
    public abstract void display();

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}

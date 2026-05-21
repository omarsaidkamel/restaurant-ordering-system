package com.restaurant.repository;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {

    private List<T> items;
    private Class<T> type;

    public Repository(Class<T> type) {
        this.type = type;
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public List<T> findAll() {
        return items;
    }

    public int count() {
        System.out.println("Total " + type.getSimpleName() + ": " + items.size());
        return items.size();
    }
}
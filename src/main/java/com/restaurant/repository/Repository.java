package com.restaurant.repository;

import com.restaurant.entity.Identifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Repository<T extends Identifiable> {

    private List<T> items;
    private Class<T> type;

    public Repository(Class<T> type) {
        this.type = type;
        this.items = new ArrayList<>();
    }

    public Optional<T> findById(Integer id) {
        return items.stream()
                .filter(item -> Objects.equals(item.getId(), id))
                .findFirst();
    }

    public boolean existsById(Integer id) {
        return items.stream()
                .anyMatch(item -> Objects.equals(item.getId(), id));
    }

    public boolean deleteById(Integer id) {
        return items.removeIf(item -> Objects.equals(item.getId(), id));
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
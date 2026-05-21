package com.restaurant.exception;

public class OrderNotPlacedException extends Exception {
    public OrderNotPlacedException() {}
    public OrderNotPlacedException(String message) {
        super(message);
    }
    public OrderNotPlacedException(String message, Throwable cause) {
        super(message, cause);
    }
}

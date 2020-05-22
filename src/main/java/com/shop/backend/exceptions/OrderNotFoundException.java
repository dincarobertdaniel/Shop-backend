package com.shop.backend.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Integer orderId) {
        super("Could not find order : " + orderId);
    }
    public OrderNotFoundException(Integer customerId, Integer orderId){
        super("Could not find order : " + orderId + " for customer : " + customerId);
    }
}

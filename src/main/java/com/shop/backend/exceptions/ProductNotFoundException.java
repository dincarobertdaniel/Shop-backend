package com.shop.backend.exceptions;


public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String productName) {
        super("Could not find product : " + productName);
    }

    public ProductNotFoundException(Integer productId) {
        super("Could not find product with id : " + productId);
    }
}

package com.shop.backend.exceptions;



public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String productName) {
        super("Product : " + productName + " already exists");
    }
}

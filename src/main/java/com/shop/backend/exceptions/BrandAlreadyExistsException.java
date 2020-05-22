package com.shop.backend.exceptions;


public class BrandAlreadyExistsException extends RuntimeException {
    public BrandAlreadyExistsException(String brandName) {
        super("Brand : " + brandName + " already exists in the database");
    }
}

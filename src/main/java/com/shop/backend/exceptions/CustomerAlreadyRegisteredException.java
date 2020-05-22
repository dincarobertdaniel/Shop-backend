package com.shop.backend.exceptions;


public class CustomerAlreadyRegisteredException extends RuntimeException {
    public CustomerAlreadyRegisteredException(String userName) {
        super("Username :" + userName + " : already exists");
    }
}

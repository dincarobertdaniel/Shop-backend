package com.shop.backend.exceptions;


public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(int id) {
        super("Could not find customer : " + id);
    }

    public CustomerNotFoundException(String userName) {
        super("Could not find customer with username : " + userName);
    }
}

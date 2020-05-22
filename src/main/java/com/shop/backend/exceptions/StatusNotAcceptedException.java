package com.shop.backend.exceptions;

public class StatusNotAcceptedException extends RuntimeException {
    public StatusNotAcceptedException(String status){
        super("Wrong order status : " +status);
    }
}

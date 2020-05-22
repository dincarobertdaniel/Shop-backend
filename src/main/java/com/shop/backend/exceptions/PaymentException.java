package com.shop.backend.exceptions;


public class PaymentException extends RuntimeException {
    public PaymentException(String error) {
        super("An error has occurred, payment status : " + error);
    }
}

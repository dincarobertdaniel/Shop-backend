package com.shop.backend.exceptions;


public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(Integer paymentId, Integer customerId) {
        super("Payment id : " + paymentId + " for customer :" + customerId + " not found");
    }

}

package com.shop.backend.exceptions;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException(Integer invoiceId) {
        super("Could not find invoice : " + invoiceId);
    }
}

package com.shop.backend.exceptions;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(String brandName) {
        super("Could not find brand : " + brandName);
    }

    public BrandNotFoundException(Integer brandId) {
        super("Could not find brand : " + brandId);
    }
}

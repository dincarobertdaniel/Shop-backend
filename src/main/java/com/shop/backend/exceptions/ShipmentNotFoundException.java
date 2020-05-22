package com.shop.backend.exceptions;


public class ShipmentNotFoundException extends RuntimeException {

    public ShipmentNotFoundException(Integer shipmentId) {
        super("Could not find shipment : " + shipmentId);
    }

    public ShipmentNotFoundException(String trackingNumber) {
        super("Shipment with tracking number : " + trackingNumber + " does not exist");
    }

}

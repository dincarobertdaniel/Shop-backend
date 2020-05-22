package com.shop.backend.service;

import com.shop.backend.exceptions.OrderNotFoundException;
import com.shop.backend.exceptions.ShipmentNotFoundException;
import com.shop.backend.exceptions.StatusNotAcceptedException;
import com.shop.backend.model.*;
import com.shop.backend.repository.CustomerRepository;
import com.shop.backend.repository.OrderRepository;
import com.shop.backend.repository.ShipmentRepository;
import com.shop.backend.exceptions.CustomerNotFoundException;
import com.shopBackEnd.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;


    public Set<Shipment> getAllShipmentsForACustomer(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            return customer.getShipments();
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }

    public Shipment getCustomerShipment(Integer customerId, Integer shipmentId) {
        if (customerRepository.findById(customerId).isPresent()) {
            return shipmentRepository.findById(shipmentId)
                    .orElseThrow(() -> new ShipmentNotFoundException(shipmentId));
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }

    public Shipment getShipmentByTrackingNumber(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new ShipmentNotFoundException(trackingNumber));
    }

    public Shipment addShipmentToCustomer(Integer customerId, Integer orderId, Shipment newShipment) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            Order order = orderRepository.findById(orderId).orElse(null);
            if (order != null) {
                if (order.getOrderStatus().equals(OrderStatus.PAYMENT_ACCEPTED)) {

                    if (newShipment.getShippingAddress() == null || newShipment.getBillingAddress() == null) {
                        newShipment.setShippingAddress(customer.getAddress());
                        newShipment.setBillingAddress(customer.getAddress());
                    }

                    newShipment.setStatus(ShipmentStatus.PENDING);
                    newShipment.setOrder(order);
                    customer.getShipments().add(newShipment);
                    newShipment.setCustomer(customer);
                    customerRepository.save(customer);

                } else {
                    System.out.println("Awaiting order payment");
                }

              return newShipment;

            } else {
                throw new OrderNotFoundException(orderId);
            }
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }

    public Shipment updateShipmentStatus(Integer shipmentId, String updateStatus) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElse(null);
        if (shipment != null) {
            boolean isShipmentStatusValid = statusValidation(updateStatus.toUpperCase());
            if(isShipmentStatusValid){
                ShipmentStatus shipmentStatus = ShipmentStatus.valueOf(updateStatus.toUpperCase());
                shipment.setStatus(shipmentStatus);
                return shipmentRepository.save(shipment);
            }else{
                throw new StatusNotAcceptedException(updateStatus);
            }
        } else {
            throw new ShipmentNotFoundException(shipmentId);
        }
    }

    private boolean statusValidation(String status){
        for(ShipmentStatus shipmentStatus : ShipmentStatus.values()){
            if(shipmentStatus.toString().equals(status)){
                return true;
            }
        }
        return false;
    }
}

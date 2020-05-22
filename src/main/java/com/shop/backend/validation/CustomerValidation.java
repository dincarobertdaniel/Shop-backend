package com.shop.backend.validation;

import com.shop.backend.model.Customer;
import com.shop.backend.repository.CustomerRepository;

public class CustomerValidation {

    public Customer checkCustomer(Customer newCustomer, CustomerRepository customerRepository) {

        Customer oldCustomer = customerRepository.findById(newCustomer.getId()).orElse(null);

        if (oldCustomer != null) {
            if (newCustomer.getUserName() == null) {
                newCustomer.setUserName(oldCustomer.getUserName());
            } else {
                oldCustomer.setUserName(newCustomer.getUserName());
            }

            if (newCustomer.getPassword() == null) {
                newCustomer.setPassword(oldCustomer.getPassword());
            } else {
                oldCustomer.setPassword(newCustomer.getPassword());
            }

            if (newCustomer.getFirstName() == null) {
                newCustomer.setFirstName(oldCustomer.getFirstName());
            } else {
                oldCustomer.setFirstName(newCustomer.getFirstName());
            }

            if (newCustomer.getLastName() == null) {
                newCustomer.setLastName(oldCustomer.getLastName());
            } else {
                oldCustomer.setLastName(newCustomer.getLastName());
            }

            if (newCustomer.getPhoneNumber() == null) {
                newCustomer.setPhoneNumber(oldCustomer.getPhoneNumber());
            } else {
                oldCustomer.setPhoneNumber(newCustomer.getPhoneNumber());
            }

            if (newCustomer.getAddress() == null) {
                newCustomer.setAddress(oldCustomer.getAddress());
            } else {
                oldCustomer.setAddress(newCustomer.getAddress());
            }

            if (newCustomer.getCity() == null) {
                newCustomer.setCity(oldCustomer.getCity());
            } else {
                oldCustomer.setCity(newCustomer.getCity());
            }

            if (newCustomer.getCountry() == null) {
                newCustomer.setCountry(oldCustomer.getCountry());
            } else {
                oldCustomer.setCountry(newCustomer.getCountry());
            }

            if (newCustomer.getEmail() == null) {
                newCustomer.setEmail(oldCustomer.getEmail());
            } else {
                oldCustomer.setEmail(newCustomer.getEmail());
            }

            if (newCustomer.getZipCode() == null) {
                newCustomer.setZipCode(oldCustomer.getZipCode());
            } else {
                oldCustomer.setZipCode(newCustomer.getZipCode());
            }

            newCustomer.setInvoices(oldCustomer.getInvoices());
            newCustomer.setOrders(oldCustomer.getOrders());
            newCustomer.setShipments(oldCustomer.getShipments());
            newCustomer.setPayments(oldCustomer.getPayments());
        }
        return newCustomer;
    }
}

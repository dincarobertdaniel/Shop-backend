package com.shop.backend.dto;


import com.shop.backend.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String email;
    private String zipCode;

    public Customer toCustomer(){
        Customer customer = new Customer();
        customer.setId(this.getId());
        customer.setUserName(this.getUserName());
        customer.setPassword(this.getPassword());
        customer.setFirstName(this.getFirstName());
        customer.setLastName(this.getLastName());
        customer.setPhoneNumber(this.getPhoneNumber());
        customer.setAddress(this.getAddress());
        customer.setCity(this.getCity());
        customer.setCountry(this.getCountry());
        customer.setEmail(this.getEmail());
        customer.setZipCode(this.getZipCode());
        return customer;
    }
}

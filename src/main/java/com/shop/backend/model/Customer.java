package com.shop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    @Size(min=5)
    @Column(name="user_name",nullable = false)
    private String userName;

    @Size(min=8)
    @Column(name="password",nullable = false)
    private String password;

    @Size(min=2,max = 256)
    @Column(name="first_name")
    private String firstName;

    @Size(min=2)
    @Column(name="last_name")
    private String lastName;

    @Size(min=5)
    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="country")
    private String country;
    @NotEmpty(message = "{error.empty.email}")
    @Column(name = "email",unique = true)
    private String email;
    @Column(name="zip_code")
    private String zipCode;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Invoice> invoices;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Payment> payments;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Shipment> shipments;

}

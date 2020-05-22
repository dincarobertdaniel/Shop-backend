package com.shop.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer shipmentId;

    @Column(name = "shipping_date",updatable = false,nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date shippingDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private ShipmentStatus status;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "shipping_address")
    private String shippingAddress;
    @Column(name = "billing_address")
    private String billingAddress;
    @Column(name = "delivery_cost")
    private Double deliveryCost;

    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

}

package com.shop.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer invoiceId;

    private BigDecimal amount;

    @Column(updatable = false,nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "invoice")
    private Order order;

    @OneToOne(mappedBy = "invoice",cascade = CascadeType.ALL)
    private Payment payment;

    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    @JsonIgnore
    public Order getOrder() {
        return order;
    }

}

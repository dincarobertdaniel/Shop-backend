package com.shop.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @Min(0)
    private BigDecimal amount;

    @Column(updatable = false,nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datePlaced;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    @JsonIgnore
    public Payment getPayment() {
        return payment;
    }

}

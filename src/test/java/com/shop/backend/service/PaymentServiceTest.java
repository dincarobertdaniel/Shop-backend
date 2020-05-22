package com.shop.backend.service;

import com.shop.backend.model.*;
import com.shop.backend.repository.CustomerRepository;
import com.shop.backend.repository.OrderRepository;
import com.shop.backend.repository.PaymentsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.shop.backend.model.OrderStatus.AWAITING_PAYMENT;


class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentsRepository paymentsRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;


    private Customer customer = new Customer(1,"UserName",
            "Password","FirstName","LastName",
            "0123456","Address","City","Country",
            "email@email.com","051410",null,null,null,null);

    private Order order = new Order(1,2,customer,null,
            new BigDecimal(300),null, AWAITING_PAYMENT,null,null);

    private Payment payment = new Payment(1,null,new BigDecimal(300),
                        true, PaymentMethod.CREDIT_CARD,customer,null,order);

    private Invoice invoice = new Invoice(1,new BigDecimal(300),new Date(System.currentTimeMillis()),customer,null,null);

    private Set<Payment> paymentList;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);

        paymentList = new HashSet<>();
        paymentList.add(payment);

        Set<Order> orders = new HashSet<>();
        orders.add(order);
        Set<Invoice> invoices = new HashSet<>();
        invoices.add(invoice);

        customer.setOrders(orders);
        customer.setInvoices(invoices);
        customer.setPayments(paymentList);
        payment.setInvoice(invoice);

        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(paymentsRepository.findByCustomerId(customer.getId())).thenReturn(paymentList);
        Mockito.when(paymentsRepository.findById(payment.getPaymentId())).thenReturn(Optional.of(payment));
        Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));

    }

    @Test
    void testGetCustomerPayments() {
        Set<Payment> payments = paymentService.getCustomerPayments(customer.getId());
        Assertions.assertEquals(payments,this.paymentList);
    }

    @Test
    void testGetCustomerPayment() {
        Payment payment = paymentService.getCustomerPayment(customer.getId(),this.payment.getPaymentId());
        Assertions.assertEquals(payment,this.payment);
    }

    @Test
    void testAddNewPayment() {
        Payment payment = paymentService.addNewPayment(this.payment,customer.getId(),order.getOrderId());
        Assertions.assertEquals(payment,this.payment);
    }
}

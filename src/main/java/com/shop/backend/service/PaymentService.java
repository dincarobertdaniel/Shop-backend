package com.shop.backend.service;

import com.shop.backend.exceptions.OrderNotFoundException;
import com.shop.backend.exceptions.PaymentException;
import com.shop.backend.exceptions.PaymentNotFoundException;
import com.shop.backend.model.*;
import com.shop.backend.repository.CustomerRepository;
import com.shop.backend.repository.InvoiceRepository;
import com.shop.backend.repository.OrderRepository;
import com.shop.backend.repository.PaymentsRepository;
import com.shop.backend.exceptions.CustomerNotFoundException;
import com.shopBackEnd.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Set;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;


    public Set<Payment> getCustomerPayments(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            Set<Payment> payments = paymentsRepository.findByCustomerId(customerId);
            if (!payments.isEmpty()) {
                return payments;
            } else {
                System.out.println("Customer : " + customerId + " has no payments");
                return payments;
            }
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }

    public Payment getCustomerPayment(Integer customerId, Integer paymentId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            Payment payment = paymentsRepository.findById(paymentId).orElse(null);
            if (payment != null) {
                return payment;
            } else {
                throw new PaymentNotFoundException(paymentId, customerId);
            }
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }

    public Payment addNewPayment(Payment newPayment, Integer customerId, Integer orderId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            Order order = orderRepository.findById(orderId).orElse(null);
            if (order != null && customer.getOrders().contains(order)) {
                if (newPayment.getAmount().equals(order.getAmount())) {
                    order.setOrderStatus(OrderStatus.PAYMENT_ACCEPTED);

                    Invoice invoice = new Invoice();

                    invoice.setAmount(newPayment.getAmount());
                    invoice.setInvoiceDate(new Date(System.currentTimeMillis()));
                    invoice.setCustomer(customer);
                    invoice.setOrder(order);
                    invoice.setPayment(newPayment);

                    invoiceRepository.save(invoice);

                    customer.getInvoices().add(invoice);

                    order.setInvoice(invoice);

                    newPayment.setInvoice(invoice);
                    newPayment.setOrder(order);
                    newPayment.setCustomer(customer);

                    customer.getPayments().add(newPayment);
                    customerRepository.save(customer);

                    return newPayment;
                } else {
                    throw new PaymentException("Your Payment Could Not Be Processed");
                }
            } else {
                throw new OrderNotFoundException(customerId,orderId);
            }
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }
}


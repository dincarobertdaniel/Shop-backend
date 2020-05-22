package com.shop.backend.service;


import com.shop.backend.model.Customer;
import com.shop.backend.model.Invoice;
import com.shop.backend.repository.CustomerRepository;
import com.shop.backend.repository.InvoiceRepository;
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

public class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer = new Customer(1,"UserName",
            "Password","FirstName","LastName",
            "0123456","Address","City","Country",
            "email@email.com","051410",null,null,null,null);

    private Set<Invoice> invoiceList = new HashSet<>();

    private Invoice invoice = new Invoice(1,new BigDecimal(1000),null,customer,null,null);

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Date date = new Date(System.currentTimeMillis());
        invoice.setInvoiceDate(date);

        Invoice invoice2 = new Invoice(2,new BigDecimal(2000),date,customer,null,null);
        invoiceList.add(invoice);
        invoiceList.add(invoice2);
        customer.setInvoices(invoiceList);
        customer.getInvoices().add(invoice);
        customer.getInvoices().add(invoice2);


        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(invoiceRepository.findByCustomerId(customer.getId())).thenReturn(invoiceList);
        Mockito.when(invoiceRepository.findById(invoice.getInvoiceId())).thenReturn(Optional.of(invoice));
    }

    @Test
    public void getCustomerInvoices() {
        Set<Invoice> invoices = invoiceService.getCustomerInvoices(customer.getId());
        Assertions.assertEquals(invoiceList,invoices);
    }

    @Test
    public void getCustomerInvoice() {
        Invoice invoice = invoiceService.getCustomerInvoice(this.invoice.getInvoiceId());
        Assertions.assertEquals(this.invoice,invoice);
    }
}

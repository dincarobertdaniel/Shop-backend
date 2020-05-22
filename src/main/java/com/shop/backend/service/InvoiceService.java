package com.shop.backend.service;

import com.shop.backend.exceptions.InvoiceNotFoundException;
import com.shop.backend.repository.CustomerRepository;
import com.shop.backend.repository.InvoiceRepository;
import com.shop.backend.exceptions.CustomerNotFoundException;
import com.shop.backend.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Set<Invoice> getCustomerInvoices(Integer customerId) {
        if (customerRepository.findById(customerId).isPresent()) {
            Set<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);
            if (!invoices.isEmpty()) {
                return invoices;
            } else {
                System.out.println("Customer : " + customerId + " has no invoices");
                return invoices;
            }
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }

    public Invoice getCustomerInvoice(Integer invoiceId) {
            return invoiceRepository.findById(invoiceId)
                    .orElseThrow(()-> new InvoiceNotFoundException(invoiceId));
    }

}

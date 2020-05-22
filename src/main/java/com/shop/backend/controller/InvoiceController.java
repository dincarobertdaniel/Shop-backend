package com.shop.backend.controller;


import com.shop.backend.model.Invoice;
import com.shop.backend.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/search")
    public Set<Invoice> getCustomerInvoices(@RequestParam Integer customerId) {
        return invoiceService.getCustomerInvoices(customerId);
    }

    @GetMapping("/search/")
    public Invoice getCustomerInvoice(@RequestParam Integer invoiceId) {
        return invoiceService.getCustomerInvoice(invoiceId);
    }

}

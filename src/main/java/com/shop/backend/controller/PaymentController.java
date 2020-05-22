package com.shop.backend.controller;


import com.shop.backend.service.PaymentService;
import com.shop.backend.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/search")
    public Set<Payment> getCustomerPayments(@RequestParam Integer customerId) {
        return paymentService.getCustomerPayments(customerId);
    }

    @GetMapping("/search/")
    public Payment getCustomerPayment(@RequestParam Integer customerId, @RequestParam Integer paymentId) {
        return paymentService.getCustomerPayment(customerId, paymentId);
    }

    @PostMapping("/add")
    public Payment addPayment(@RequestBody Payment newPayment, @RequestParam Integer customerId, @RequestParam Integer orderId) {
        return paymentService.addNewPayment(newPayment, customerId, orderId);
    }


}

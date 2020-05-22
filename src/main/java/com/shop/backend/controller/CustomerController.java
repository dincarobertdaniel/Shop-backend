package com.shop.backend.controller;

import com.shop.backend.dto.CustomerDto;
import com.shop.backend.model.Customer;
import com.shop.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/search/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/search")
    public Customer getCustomerById(@RequestParam Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping("/search/{userName}")
    public Customer getCustomerByName(@PathVariable String userName) {
        return customerService.getCustomerByUserName(userName);
    }

    @PostMapping("/add")
    public Customer createCustomer(@RequestBody Customer newCustomer) {
        return customerService.createNewCustomer(newCustomer);
    }

    @PatchMapping("/update")
    public Customer updateCustomer(@RequestBody CustomerDto newCustomer){
         return customerService.updateCustomer(newCustomer.toCustomer());
    }

    @DeleteMapping("/delete")
    public void deleteCustomer(@RequestParam Integer customerId) {
        customerService.deleteCustomer(customerId);
    }

}

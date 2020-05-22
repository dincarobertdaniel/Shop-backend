package com.shop.backend.service;

import com.shop.backend.exceptions.CustomerAlreadyRegisteredException;
import com.shop.backend.exceptions.CustomerNotFoundException;
import com.shop.backend.model.Customer;
import com.shop.backend.repository.CustomerRepository;
import com.shop.backend.validation.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;



@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

     public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

     public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Customer getCustomerByUserName(String userName) {
        Customer customer =  customerRepository.findByUserName(userName);
        if(customer != null){
            return customer;
        }else{
            throw new CustomerNotFoundException(userName);
        }
    }

    public Customer createNewCustomer(Customer newCustomer) {
        Customer customer =  customerRepository.findByUserName(newCustomer.getUserName());
        if(customer == null){
            return customerRepository.save(newCustomer);
        }else{
            throw new CustomerAlreadyRegisteredException(newCustomer.getUserName());
        }
    }

    public Customer updateCustomer(Customer updateCustomer){
        Customer customer =  new CustomerValidation().checkCustomer(updateCustomer,customerRepository);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException(id);
        }
    }
}

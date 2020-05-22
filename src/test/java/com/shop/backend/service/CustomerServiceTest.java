package com.shop.backend.service;

import com.shop.backend.model.Customer;
import com.shop.backend.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;


    private Customer customer = new Customer(1,"UserName",
            "Password","FirstName","LastName",
            "0123456","Address","City","Country",
            "email@email.com","051410",null,null,null,null);

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);

        List<Customer> customerList = new ArrayList<>();
        Customer customer2 = new Customer(2,"UserName2",
                "Password","FirstName","LastName",
                "0123456","Address","City","Country",
                "email@email.com","051410",null,null,null,null);

        Customer customer3 = new Customer(3,"UserName3",
                "Password","FirstName","LastName",
                "0123456","Address","City","Country",
                "email@email.com","051410",null,null,null,null);

        customerList.add(customer2);
        customerList.add(customer3);

        Mockito.when(customerRepository.findAll()).thenReturn(customerList);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        Mockito.when(customerRepository.findByUserName(customer.getUserName())).thenReturn(customer);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        Assertions.assertEquals(2,customerList.size());
        Mockito.verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = customerService.getCustomerById(this.customer.getId());
        Assertions.assertEquals(customer,this.customer);
    }

    @Test
    public void testGetCustomerByName() {
        Customer customer = customerService.getCustomerByUserName(this.customer.getUserName());
        Assertions.assertEquals(customer,this.customer);
    }

    @Test
    public void testCreateNewCustomer() {
        Mockito.when(customerRepository.findByUserName(customer.getUserName())).thenReturn(null);
        Customer customer = customerService.createNewCustomer(this.customer);
        Assertions.assertEquals(customer,this.customer);
    }

    @Test
    public void testUpdateCustomer() {
        Customer updateCustomer = new Customer(1,"UserNameUpdate",
                "PasswordUpdate","FirstNameUpdate",null,
                null,null,null,null,
                null,null,null,null,null,null);

        Customer updated = new Customer(1,"UserNameUpdate",
                "PasswordUpdate","FirstNameUpdate", "LastName",
                "0123456","Address","City","Country",
                "email@email.com","051410",null,null,null,null);


        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(updated);

        Customer customer = customerService.updateCustomer(updateCustomer);

        Assertions.assertEquals(customer,updated);

        Mockito.verify(customerRepository,times(1)).save(updateCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        customerService.deleteCustomer(customer.getId());
        Mockito.verify(customerRepository, times(1)).deleteById(customer.getId());
    }
}

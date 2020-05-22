package com.shop.backend.service;

import com.shop.backend.model.Customer;
import com.shop.backend.model.Order;
import com.shop.backend.model.Product;
import com.shop.backend.repository.CustomerRepository;
import com.shop.backend.repository.OrderRepository;
import com.shop.backend.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

import static com.shop.backend.model.OrderStatus.AWAITING_PAYMENT;
import static com.shop.backend.model.OrderStatus.PAYMENT_ACCEPTED;


class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    private Customer customer = new Customer(1,"UserName",
            "Password","FirstName","LastName",
            "0123456","Address","City","Country",
            "email@email.com","051410",null,null,null,null);

    private List<Product> products = new ArrayList<>();

    private Order order = new Order(1,2,customer,null,
            new BigDecimal(300),null, AWAITING_PAYMENT,null,null);

    private Product product = new Product(1,"ProductName","ProductDescription",100,
            new BigDecimal(100),null,null,null);

    private Set<Order> orders = new HashSet<>();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);

        Product product2 = new Product(2,"ProductName2","ProductDescription2",200,
                new BigDecimal(200),null,null,null);

        products.add(product);
        products.add(product2);

        order.setProducts(products);
        orders.add(order);
        customer.setOrders(orders);

        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Mockito.when(productRepository.findById(product2.getId())).thenReturn(Optional.of(product2));
    }


    @Test
    void testGetAllCustomerOrders() {
        Set<Order> orders = orderService.getAllCustomerOrders(customer.getId());
        Assertions.assertEquals(orders,this.orders);
    }

    @Test
    void testGetCustomerOrder() {
        Order order = orderService.getCustomerOrder(this.order.getOrderId());
        Assertions.assertEquals(order,this.order);
    }

    @Test
    void testAddNewOrder() {
        List<Integer> productsIds = new ArrayList<>();
        productsIds.add(1);
        productsIds.add(2);

        Date date = new Date(System.currentTimeMillis());
        this.order.setDatePlaced(date);

        Order order = orderService.addNewOrder(customer.getId(), productsIds);
        order.setOrderId(1);
        order.setDatePlaced(date);

        Assertions.assertEquals(order,this.order);
    }

    @Test
    void testUpdateOrder() {
        this.order.setOrderStatus(PAYMENT_ACCEPTED);
        Order updatedOrder = orderService.updateOrder(customer.getId(),this.order.getOrderId(),"PAYMENT_ACCEPTED");
        Assertions.assertEquals(updatedOrder,this.order);
    }
}

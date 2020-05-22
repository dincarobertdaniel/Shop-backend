package com.shop.backend.service;

import com.shop.backend.exceptions.OrderNotFoundException;
import com.shop.backend.exceptions.ProductNotFoundException;
import com.shop.backend.exceptions.StatusNotAcceptedException;
import com.shop.backend.repository.CustomerRepository;
import com.shop.backend.repository.OrderRepository;
import com.shop.backend.repository.ProductRepository;
import com.shop.backend.exceptions.CustomerNotFoundException;
import com.shop.backend.model.Customer;
import com.shop.backend.model.Order;
import com.shop.backend.model.OrderStatus;
import com.shop.backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public Set<Order> getAllCustomerOrders(Integer customerId) {
        if (customerRepository.findById(customerId).isPresent()) {
            return customerRepository.findById(customerId).get().getOrders();
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }

    public Order getCustomerOrder(Integer orderId) {
            return orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public Order addNewOrder(Integer customerId, List<Integer> listOfProducts) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Order newOrder = new Order();
        if (customer != null) {
            List<Product> orderedProducts = new ArrayList<>();
            BigDecimal amount = new BigDecimal("0");
            for (Integer id : listOfProducts) {
                Product product = productRepository.findById(id).orElse(null);
                if (product != null) {
                    amount = amount.add(product.getPrice());
                    orderedProducts.add(product);
                } else {
                    throw new ProductNotFoundException(id);
                }
            }
            newOrder.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
            newOrder.setDatePlaced(new Date(System.currentTimeMillis()));
            newOrder.setQuantity(listOfProducts.size());
            newOrder.setAmount(amount);
            newOrder.setProducts(orderedProducts);
            customer.getOrders().add(newOrder);
            newOrder.setCustomer(customer);
            customerRepository.save(customer);
            return newOrder;
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }

    public Order updateOrder(Integer customerId, Integer orderId, String updateOrderStatus) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            Order order = orderRepository.findById(orderId).orElse(null);
            boolean isOrderStatusValid = statusValidation(updateOrderStatus.toUpperCase());
            if (order != null) {
                if(isOrderStatusValid){
                    OrderStatus orderStatus = OrderStatus.valueOf(updateOrderStatus.toUpperCase());
                    order.setOrderStatus(orderStatus);
                    return orderRepository.save(order);
                }else{
                    throw new StatusNotAcceptedException(updateOrderStatus);
                }
            } else {
                throw new OrderNotFoundException(orderId);
            }
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }

    private boolean statusValidation(String status){
        for(OrderStatus orderStatus : OrderStatus.values()){
            if(orderStatus.toString().equals(status)){
                return true;
            }
        }
        return false;
    }

}

package com.shop.backend.controller;


import com.shop.backend.service.OrderService;
import com.shop.backend.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/search")
    public Set<Order> getAllOrders(@RequestParam Integer customerId) {
        return orderService.getAllCustomerOrders(customerId);
    }

    @GetMapping("/search/")
    public Order getCustomerOrder(@RequestParam Integer orderId) {
        return orderService.getCustomerOrder( orderId);
    }

    @PostMapping("/add")
    public Order addNewOrder(@RequestParam Integer customerId, @RequestParam List<Integer> listOfProducts) {
        return orderService.addNewOrder(customerId, listOfProducts);
    }

    @PatchMapping("/update")
    public Order updateOrder(@RequestParam Integer customerId, @RequestParam Integer orderId, @RequestParam String updateOrder) {
       return orderService.updateOrder(customerId, orderId, updateOrder);
    }

}

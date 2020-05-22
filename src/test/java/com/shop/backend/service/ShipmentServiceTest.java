package com.shop.backend.service;

import com.shop.backend.model.Customer;
import com.shop.backend.model.Order;
import com.shop.backend.model.Shipment;
import com.shop.backend.model.ShipmentStatus;
import com.shop.backend.repository.CustomerRepository;
import com.shop.backend.repository.OrderRepository;
import com.shop.backend.repository.ShipmentRepository;
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

import static com.shop.backend.model.OrderStatus.PAYMENT_ACCEPTED;

class ShipmentServiceTest {

    @InjectMocks
    private ShipmentService shipmentService;

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    private Customer customer = new Customer(1,"UserName",
            "Password","FirstName","LastName",
            "0123456","Address","City","Country",
            "email@email.com","051410",null,null,null,null);

    private Shipment shipment = new Shipment(1,new Date(System.currentTimeMillis()),
            ShipmentStatus.PACKED,"TN12345RO",null,customer,null,null,20.0d);

    private Order order = new Order(1,2,customer,null,
            new BigDecimal(300),null, PAYMENT_ACCEPTED,null,null);

    private Set<Shipment> shipments;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);

        shipment.setOrder(order);

        shipments = new HashSet<>();
        shipments.add(shipment);

        customer.setShipments(shipments);

        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(shipmentRepository.findById(shipment.getShipmentId())).thenReturn(Optional.of(shipment));
        Mockito.when(shipmentRepository.findByTrackingNumber(shipment.getTrackingNumber())).thenReturn(Optional.of(shipment));
        Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
        Mockito.when(shipmentRepository.save(shipment)).thenReturn(shipment);
    }

    @Test
    void testGetAllShipmentsForACustomer() {
        Set<Shipment> shipments = shipmentService.getAllShipmentsForACustomer(customer.getId());
        Assertions.assertEquals(shipments,this.shipments);
    }

    @Test
    void testGetAShipmentForACustomer() {
        Shipment shipment = shipmentService.getCustomerShipment(this.customer.getId(),this.shipment.getShipmentId());
        Assertions.assertEquals(shipment,this.shipment);
    }

    @Test
    void testGetShipmentByTrackingNumber() {
        Shipment shipment = shipmentService.getShipmentByTrackingNumber(this.shipment.getTrackingNumber());
        Assertions.assertEquals(shipment,this.shipment);
    }

    @Test
    void testAddShipmentToCustomer() {
        Shipment shipment = shipmentService.addShipmentToCustomer(this.customer.getId(),
                this.order.getOrderId(),this.shipment);
        Assertions.assertEquals(shipment,this.shipment);
    }

    @Test
    void testUpdateShipmentStatus() {
        this.shipment.setStatus(ShipmentStatus.PACKED);
        Shipment shipment = shipmentService.updateShipmentStatus(this.shipment.getShipmentId(),"Packed");
        Assertions.assertEquals(shipment,this.shipment);
    }
}

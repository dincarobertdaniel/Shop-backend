package com.shop.backend.controller;

import com.shop.backend.service.ShipmentService;
import com.shop.backend.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/search")
    public Set<Shipment> getAllShipmentsForACustomer(@RequestParam Integer customerId) {
        return shipmentService.getAllShipmentsForACustomer(customerId);
    }

    @GetMapping("/search/")
    public Shipment getAllShipmentsForACustomer(@RequestParam Integer customerId, @RequestParam Integer shipmentId) {
        return shipmentService.getCustomerShipment(customerId, shipmentId);
    }

    @GetMapping("search/{trackingNumber:[A-Za-z]+[0-9]+RO$}")
    public Shipment getShipmentByTrackingNumber(@PathVariable String trackingNumber) {
        return shipmentService.getShipmentByTrackingNumber(trackingNumber);
    }

    @PostMapping("/add")
    public Shipment addShipmentToCustomer(@RequestParam Integer customerId, @RequestParam Integer orderId, @RequestBody Shipment newShipment) {
         return  shipmentService.addShipmentToCustomer(customerId,orderId, newShipment);
    }

    @PatchMapping("/update")
    public Shipment updateShipmentStatus(@RequestParam Integer shipmentId, @RequestParam String updateStatus) {
        return shipmentService.updateShipmentStatus(shipmentId, updateStatus);
    }
}

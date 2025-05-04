package com.fraud.detection.shipment.service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fraud.detection.shipment.service.service.ShipmentDeliveryService;
import com.order.lifecycle.common.libs.model.ShipmentDispatchedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShipmentDispatchedListener {
 
    private final ShipmentDeliveryService deliveryService;
 
    @KafkaListener(topics = "shipment-dispatched-1", groupId = "shipment-delivered-service")
    public void handleShipmentDispatched(ShipmentDispatchedEvent event) {
        deliveryService.deliverShipment(event);
    }
}
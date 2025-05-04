package com.fraud.detection.order.service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fraud.detection.order.service.service.impl.OrderServiceImpl;
import com.order.lifecycle.common.libs.model.ShipmentDeliveredEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShipmentDeliveredListener {
 
    private final OrderServiceImpl completionService;
 
    @KafkaListener(topics = "shipment-delivered-1", groupId = "order-completion-service")
    public void onShipmentDelivered(ShipmentDeliveredEvent event) {
        completionService.completeOrder(event);
    }
}

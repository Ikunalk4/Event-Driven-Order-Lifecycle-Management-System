package com.fraud.detection.shipment.service.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fraud.detection.shipment.service.repo.ShipmentDeliveryRepository;
import com.fraud.detection.shipment.service.service.ShipmentDeliveryService;
import com.order.lifecycle.common.libs.model.DeliveryStatus;
import com.order.lifecycle.common.libs.model.ShipmentDeliveredEvent;
import com.order.lifecycle.common.libs.model.ShipmentDelivery;
import com.order.lifecycle.common.libs.model.ShipmentDispatchedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentDeliveryServiceImpl implements ShipmentDeliveryService {
 
    private final ShipmentDeliveryRepository deliveryRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
 
    @Override
    public ShipmentDelivery deliverShipment(ShipmentDispatchedEvent event) {
        // Simulate delivery delay or random failure
        boolean delivered = new Random().nextInt(10) != 0; // 10% failure chance
 
        ShipmentDelivery delivery = ShipmentDelivery.builder()
                .orderId(event.getOrderId())
                .userId(event.getUserId())
                .trackingId(event.getTrackingId())
                .deliveredAt(LocalDateTime.now())
                .status(delivered ? DeliveryStatus.DELIVERED : DeliveryStatus.FAILED)
                .build();
 
        deliveryRepository.save(delivery);
 
        if (delivered) {
            ShipmentDeliveredEvent deliveredEvent = ShipmentDeliveredEvent.builder()
                    .orderId(event.getOrderId())
                    .userId(event.getUserId())
                    .trackingId(event.getTrackingId())
                    .deliveredAt(delivery.getDeliveredAt())
                    .build();
            kafkaTemplate.send("shipment-delivered-1", deliveredEvent.getOrderId(), deliveredEvent);
        }
 
        return delivery;
    }
}
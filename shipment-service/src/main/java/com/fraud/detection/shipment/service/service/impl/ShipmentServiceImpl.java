package com.fraud.detection.shipment.service.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fraud.detection.shipment.service.repo.ShipmentRepository;
import com.fraud.detection.shipment.service.service.ShipmentService;
import com.order.lifecycle.common.libs.model.PaymentSuccessEvent;
import com.order.lifecycle.common.libs.model.Shipment;
import com.order.lifecycle.common.libs.model.ShipmentPreparedEvent;
import com.order.lifecycle.common.libs.model.ShipmentStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
 
    private final ShipmentRepository shipmentRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
 
    @Override
    public Shipment prepareShipment(PaymentSuccessEvent event) {
        // Simulated shipment logic
        String trackingId = UUID.randomUUID().toString();
 
        Shipment shipment = Shipment.builder()
                .orderId(event.getOrderId())
                .userId(event.getUserId())
                .shippingAddress("Default Address") // Normally fetched from DB or order
                .status(ShipmentStatus.PREPARED)
                .preparedAt(LocalDateTime.now())
                .build();
 
        shipmentRepository.save(shipment);
 
        ShipmentPreparedEvent shipmentPreparedEvent = ShipmentPreparedEvent.builder()
                .orderId(event.getOrderId())
                .userId(event.getUserId())
                .trackingId(trackingId)
                .shippingAddress(shipment.getShippingAddress())
                .preparedAt(LocalDateTime.now())
                .build();
 
        kafkaTemplate.send("shipment-prepared-1", shipmentPreparedEvent.getOrderId(), shipmentPreparedEvent);
        return shipment;
    }
}

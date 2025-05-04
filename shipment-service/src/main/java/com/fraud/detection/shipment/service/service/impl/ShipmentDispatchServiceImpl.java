package com.fraud.detection.shipment.service.service.impl;

import java.time.LocalDateTime;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fraud.detection.shipment.service.repo.ShipmentDispatchRepository;
import com.fraud.detection.shipment.service.service.ShipmentDispatchService;
import com.order.lifecycle.common.libs.model.ShipmentDispatch;
import com.order.lifecycle.common.libs.model.ShipmentDispatchedEvent;
import com.order.lifecycle.common.libs.model.ShipmentPreparedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentDispatchServiceImpl implements ShipmentDispatchService {
 
    private final ShipmentDispatchRepository dispatchRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
 
    @Override
    public ShipmentDispatch dispatchShipment(ShipmentPreparedEvent event) {
        String logisticsPartner = "Delhivery"; // Can be random or selected by rules
 
        ShipmentDispatch dispatch = ShipmentDispatch.builder()
                .orderId(event.getOrderId())
                .userId(event.getUserId())
                .trackingId(event.getTrackingId())
                .logisticsPartner(logisticsPartner)
                .dispatchedAt(LocalDateTime.now())
                .build();
 
        dispatchRepository.save(dispatch);
 
        ShipmentDispatchedEvent dispatchedEvent = ShipmentDispatchedEvent.builder()
                .orderId(event.getOrderId())
                .userId(event.getUserId())
                .trackingId(event.getTrackingId())
                .logisticsPartner(logisticsPartner)
                .dispatchedAt(dispatch.getDispatchedAt())
                .build();
 
        kafkaTemplate.send("shipment-dispatched-1", dispatchedEvent.getOrderId(), dispatchedEvent);
        return dispatch;
    }
}

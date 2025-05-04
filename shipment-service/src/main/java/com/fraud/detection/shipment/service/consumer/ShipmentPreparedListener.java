package com.fraud.detection.shipment.service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fraud.detection.shipment.service.service.ShipmentDispatchService;
import com.order.lifecycle.common.libs.model.ShipmentPreparedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShipmentPreparedListener {
 
    private final ShipmentDispatchService dispatchService;
 
    @KafkaListener(topics = "shipment-prepared-1", groupId = "shipment-dispatch-service")
    public void handleShipmentPrepared(ShipmentPreparedEvent event) {
        dispatchService.dispatchShipment(event);
    }
}

package com.fraud.detection.shipment.service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fraud.detection.shipment.service.service.ShipmentService;
import com.order.lifecycle.common.libs.model.PaymentSuccessEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentSuccessListener {
 
    private final ShipmentService shipmentService;
 
    @KafkaListener(topics = "payment-success-2", groupId = "shipment-service")
    public void onPaymentSuccess(PaymentSuccessEvent event) {
        shipmentService.prepareShipment(event);
    }
}

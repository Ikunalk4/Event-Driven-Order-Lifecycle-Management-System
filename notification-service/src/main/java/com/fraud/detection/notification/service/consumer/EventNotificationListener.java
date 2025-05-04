package com.fraud.detection.notification.service.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fraud.detection.notification.service.service.NotificationService;
import com.order.lifecycle.common.libs.model.NotificationChannel;
import com.order.lifecycle.common.libs.model.OrderCreatedEvent;
import com.order.lifecycle.common.libs.model.PaymentFailedEvent;
import com.order.lifecycle.common.libs.model.PaymentSuccessEvent;
import com.order.lifecycle.common.libs.model.ShipmentDeliveredEvent;
import com.order.lifecycle.common.libs.model.ShipmentDispatchedEvent;
import com.order.lifecycle.common.libs.model.ShipmentPreparedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventNotificationListener {
 
    private final NotificationService notificationService;
 
    @KafkaListener(topics = {
        "order-created-1",
        "payment-success-2",
        "shipment-prepared-1",
        "shipment-dispatched-1",
        "shipment-delivered-1",
        "order-completed-1",
        "payment-failed-1"
    }, groupId = "notification-service", containerFactory = "kafkaListenerContainerFactory")
    public void handleEvents(ConsumerRecord<String, Object> record) {
        String topic = record.topic();
 
        Object value = record.value();
        String userId = ""; // Extract from value based on type
        String message = "";
 
        if (value instanceof OrderCreatedEvent event) {
            userId = event.getUserId();
            message = "Your order has been created!";
        } else if (value instanceof PaymentSuccessEvent event) {
            userId = event.getUserId();
            message = "Your payment was successful!";
        } else if (value instanceof ShipmentPreparedEvent event) {
            userId = event.getUserId();
            message = "Your shipment is prepared.";
        } else if (value instanceof ShipmentDispatchedEvent event) {
            userId = event.getUserId();
            message = "Your order has been dispatched.";
        } else if (value instanceof ShipmentDeliveredEvent event) {
            userId = event.getUserId();
            message = "Your order has been delivered.";
        }
        else if (value instanceof PaymentFailedEvent event) {
            userId = event.getUserId();
            message = "Your payment was failed.";
        }
        
        if (!userId.isEmpty()) {
            notificationService.notifyUser(userId, message, NotificationChannel.EMAIL, topic);
        }
    }
}

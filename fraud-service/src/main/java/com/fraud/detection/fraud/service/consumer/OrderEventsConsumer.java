package com.fraud.detection.fraud.service.consumer;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fraud.detection.fraud.service.service.FraudDetectionService;
import com.order.lifecycle.common.libs.model.FraudCheckResult;
import com.order.lifecycle.common.libs.model.FraudDetectedEvent;
import com.order.lifecycle.common.libs.model.OrderCreatedEvent;
import com.order.lifecycle.common.libs.model.OrderValidatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventsConsumer {
	
	private final FraudDetectionService fraudDetectionService;
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	@KafkaListener(topics = "order-created-1", groupId = "fraud-service")
	public void consumeOrderCreated(OrderCreatedEvent event) {
		log.info("OrderCreatedEvent received: " + event);
		FraudCheckResult result = fraudDetectionService.checkForFraud(event);
		
		if(result == FraudCheckResult.FRAUD) {
			FraudDetectedEvent fraudEvent = FraudDetectedEvent.builder()
					.orderId(event.getOrderId())
					.userId(event.getUserId())
					.reason("High order amount")
					.detectedAt(LocalDateTime.now())
					.build();
			kafkaTemplate.send("fraud-detected-1", fraudEvent);
		}else {
			OrderValidatedEvent validatedEvent = OrderValidatedEvent.builder()
					.orderId(event.getOrderId())
					.userId(event.getUserId())
					.amount(event.getTotalAmount())
					.result(FraudCheckResult.VALID)
					.validatedAt(LocalDateTime.now())
					.build();
			
			kafkaTemplate.send("order-validated-1", validatedEvent.getOrderId(), validatedEvent);
		}
	}
	
}

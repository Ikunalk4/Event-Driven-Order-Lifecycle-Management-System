package com.fraud.detection.payment.service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fraud.detection.payment.service.service.PaymentService;
import com.order.lifecycle.common.libs.model.OrderValidatedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderValidatedListener {
	
	private final PaymentService paymentService;
	
	@KafkaListener(topics = "order-validated-1", groupId = "payment-service")
	public void handleOrderValidated(OrderValidatedEvent event) {
		paymentService.processPayment(event);
	}
}

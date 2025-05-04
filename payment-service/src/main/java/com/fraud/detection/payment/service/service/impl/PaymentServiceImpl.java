package com.fraud.detection.payment.service.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fraud.detection.payment.service.repo.PaymentRepository;
import com.fraud.detection.payment.service.service.PaymentService;
import com.order.lifecycle.common.libs.model.OrderValidatedEvent;
import com.order.lifecycle.common.libs.model.Payment;
import com.order.lifecycle.common.libs.model.PaymentFailedEvent;
import com.order.lifecycle.common.libs.model.PaymentStatus;
import com.order.lifecycle.common.libs.model.PaymentSuccessEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	
	private final PaymentRepository paymentRepository;
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	@Override
	public Payment processPayment(OrderValidatedEvent event) {
		//simulate payment processing
		boolean success = new Random().nextBoolean();
		
		Payment payment = Payment.builder()
				.orderId(event.getOrderId())
				.userId(event.getUserId())
				.amount(event.getAmount())
				.status(success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED)
				.processedAt(LocalDateTime.now())
				.build();
		
		paymentRepository.save(payment);
		if(success) {
			PaymentSuccessEvent successEvent = PaymentSuccessEvent.builder()
					.orderId(event.getOrderId())
					.userId(event.getUserId())
					.amount(event.getAmount())
					.paidAt(LocalDateTime.now())
					.build();
			kafkaTemplate.send("payment-success-2", successEvent.getOrderId(),successEvent);
		}
		else {
			PaymentFailedEvent failedEvent = PaymentFailedEvent.builder()
					.orderId(event.getOrderId())
					.userId(event.getUserId())
					.reason("Insufficient funds") // dummy
					.failedAt(LocalDateTime.now())
					.build();
			kafkaTemplate.send("payment-failed-1", failedEvent.getOrderId(), failedEvent);
		}
		
		return payment;
	}

}

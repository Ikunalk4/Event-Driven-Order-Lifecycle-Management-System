package com.fraud.detection.payment.service.service;

import com.order.lifecycle.common.libs.model.OrderValidatedEvent;
import com.order.lifecycle.common.libs.model.Payment;

public interface PaymentService {
	
	Payment processPayment(OrderValidatedEvent event);
}

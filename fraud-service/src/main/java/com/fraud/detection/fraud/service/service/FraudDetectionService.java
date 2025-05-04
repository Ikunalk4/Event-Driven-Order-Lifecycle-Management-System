package com.fraud.detection.fraud.service.service;

import com.order.lifecycle.common.libs.model.FraudCheckResult;
import com.order.lifecycle.common.libs.model.OrderCreatedEvent;

public interface FraudDetectionService {
	
	FraudCheckResult checkForFraud(OrderCreatedEvent event);
}

package com.fraud.detection.fraud.service.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.fraud.detection.fraud.service.service.FraudDetectionService;
import com.order.lifecycle.common.libs.model.FraudCheckResult;
import com.order.lifecycle.common.libs.model.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FraudDetectionServiceImpl implements FraudDetectionService{
	
	@Override
	public FraudCheckResult checkForFraud(OrderCreatedEvent event) {
		// dummy rule-based logic (can be replaced with ML or some external API)
		if(event.getTotalAmount().compareTo(BigDecimal.valueOf(5000)) > 0) {
			return FraudCheckResult.FRAUD;
		}
		return FraudCheckResult.VALID;
	}

}

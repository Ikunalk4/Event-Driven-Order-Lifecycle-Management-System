package com.order.lifecycle.common.libs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
	
	private String orderId;
	private String userId;
	private BigDecimal totalAmount;
	private List<OrderItem> itmes;
	private LocalDateTime timeStamp;
	
}

package com.order.lifecycle.common.libs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
	
	@Id
	private String id;
	private String orderId;
	private String userId;
	private BigDecimal amount;
	private PaymentStatus status;
	private LocalDateTime processedAt;
}

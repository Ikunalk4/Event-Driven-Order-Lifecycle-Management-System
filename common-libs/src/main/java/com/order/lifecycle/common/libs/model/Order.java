package com.order.lifecycle.common.libs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
	
	@Id
	private String id;
	private String userId;
	private List<OrderItem> items;
	private BigDecimal totalAmount;
	private OrderStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}

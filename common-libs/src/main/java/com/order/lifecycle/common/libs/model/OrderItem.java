package com.order.lifecycle.common.libs.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	
	private String productId;
	private String productName;
	private int quantity;
	private BigDecimal price;
	
}

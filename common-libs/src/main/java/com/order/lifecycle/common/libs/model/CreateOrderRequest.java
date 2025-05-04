package com.order.lifecycle.common.libs.model;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateOrderRequest {
	
	@NotEmpty
	private String userId;
	
	@NotEmpty
	private List<OrderItem> items;
}

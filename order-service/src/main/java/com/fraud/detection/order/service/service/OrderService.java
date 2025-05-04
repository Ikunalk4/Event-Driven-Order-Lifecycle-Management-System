package com.fraud.detection.order.service.service;

import com.order.lifecycle.common.libs.model.CreateOrderRequest;
import com.order.lifecycle.common.libs.model.Order;
import com.order.lifecycle.common.libs.model.ShipmentDeliveredEvent;

public interface OrderService {
	Order createOrder(CreateOrderRequest orderRequest);
	
	void completeOrder(ShipmentDeliveredEvent event);
}

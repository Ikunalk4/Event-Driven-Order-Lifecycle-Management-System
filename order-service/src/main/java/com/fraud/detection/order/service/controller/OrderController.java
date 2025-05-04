package com.fraud.detection.order.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraud.detection.order.service.service.OrderService;
import com.order.lifecycle.common.libs.model.CreateOrderRequest;
import com.order.lifecycle.common.libs.model.Order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order-service/api/v1")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping("order")
	public ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderRequest request){
		Order order = orderService.createOrder(request);
		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}
}

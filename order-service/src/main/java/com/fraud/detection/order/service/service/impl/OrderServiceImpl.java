package com.fraud.detection.order.service.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fraud.detection.order.service.repo.OrderRepository;
import com.fraud.detection.order.service.service.OrderService;
import com.order.lifecycle.common.libs.model.CreateOrderRequest;
import com.order.lifecycle.common.libs.model.Order;
import com.order.lifecycle.common.libs.model.OrderCompletedEvent;
import com.order.lifecycle.common.libs.model.OrderCreatedEvent;
import com.order.lifecycle.common.libs.model.OrderItem;
import com.order.lifecycle.common.libs.model.OrderStatus;
import com.order.lifecycle.common.libs.model.ShipmentDeliveredEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
	 private final KafkaTemplate<String, Object> kafkaTemplate2;
	
	@Override
	public Order createOrder(CreateOrderRequest request) {
		Order order = Order.builder()
				.userId(request.getUserId())
				.items(request.getItems())
				.totalAmount(calculateTotal(request.getItems()))
				.status(OrderStatus.CREATED)
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.build();
		Order savedOrder = orderRepository.save(order);
		
		OrderCreatedEvent event = OrderCreatedEvent.builder()
			.orderId(savedOrder.getId())
			.userId(savedOrder.getUserId())
			.itmes(savedOrder.getItems())
			.totalAmount(savedOrder.getTotalAmount())
			.timeStamp(LocalDateTime.now())
			.build();
		
		kafkaTemplate.send("order-created-1", savedOrder.getId(), event);
		log.info("Order-created: "+ event + " orderId: " + savedOrder.getId());
		return savedOrder;
	}
	
	private BigDecimal calculateTotal(List<OrderItem> items) {
		return items.stream()
				.map( item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
    public void completeOrder(ShipmentDeliveredEvent event) {
        orderRepository.findById(event.getOrderId()).ifPresent(order -> {
            order.setStatus(OrderStatus.COMPLETED);
            orderRepository.save(order);
 
            // Produce order-completed event
            OrderCompletedEvent completedEvent = OrderCompletedEvent.builder()
                    .orderId(order.getId())
                    .userId(order.getUserId())
                    .completedAt(LocalDateTime.now())
                    .build();
 
            kafkaTemplate2.send("order-completed-1", completedEvent.getOrderId(), completedEvent);
        });
    }

}

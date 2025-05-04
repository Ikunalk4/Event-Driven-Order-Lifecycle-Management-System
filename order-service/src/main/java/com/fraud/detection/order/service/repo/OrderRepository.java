package com.fraud.detection.order.service.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.order.lifecycle.common.libs.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{
	
	 Optional<Order> findById(String orderId);
}

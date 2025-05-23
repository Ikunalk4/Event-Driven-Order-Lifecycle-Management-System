package com.fraud.detection.payment.service.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.order.lifecycle.common.libs.model.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {

}

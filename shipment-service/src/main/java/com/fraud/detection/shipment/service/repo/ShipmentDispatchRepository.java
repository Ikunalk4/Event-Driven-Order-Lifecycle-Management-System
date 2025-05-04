package com.fraud.detection.shipment.service.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.order.lifecycle.common.libs.model.ShipmentDispatch;

public interface ShipmentDispatchRepository extends MongoRepository<ShipmentDispatch, String> {
}

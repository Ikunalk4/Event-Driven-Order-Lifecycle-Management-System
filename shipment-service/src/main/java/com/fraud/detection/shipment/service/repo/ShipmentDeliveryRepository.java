package com.fraud.detection.shipment.service.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.order.lifecycle.common.libs.model.ShipmentDelivery;

public interface ShipmentDeliveryRepository extends MongoRepository<ShipmentDelivery, String> {
}

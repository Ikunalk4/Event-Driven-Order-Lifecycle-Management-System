package com.fraud.detection.shipment.service.service;

import com.order.lifecycle.common.libs.model.ShipmentDelivery;
import com.order.lifecycle.common.libs.model.ShipmentDispatchedEvent;

public interface ShipmentDeliveryService {
    ShipmentDelivery deliverShipment(ShipmentDispatchedEvent event);
}

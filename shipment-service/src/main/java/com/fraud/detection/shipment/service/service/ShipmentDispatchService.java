package com.fraud.detection.shipment.service.service;

import com.order.lifecycle.common.libs.model.ShipmentDispatch;
import com.order.lifecycle.common.libs.model.ShipmentPreparedEvent;

public interface ShipmentDispatchService {
    ShipmentDispatch dispatchShipment(ShipmentPreparedEvent event);
}

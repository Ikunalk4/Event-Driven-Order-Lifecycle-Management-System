package com.fraud.detection.shipment.service.service;

import com.order.lifecycle.common.libs.model.PaymentSuccessEvent;
import com.order.lifecycle.common.libs.model.Shipment;

public interface ShipmentService {
	 Shipment prepareShipment(PaymentSuccessEvent event);
}

package com.order.lifecycle.common.libs.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "shipment_deliveries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentDelivery {
    @Id
    private String id;
    private String orderId;
    private String userId;
    private String trackingId;
    private LocalDateTime deliveredAt;
    private DeliveryStatus status;
}

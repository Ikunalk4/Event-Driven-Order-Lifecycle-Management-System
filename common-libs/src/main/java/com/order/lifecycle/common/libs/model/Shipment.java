package com.order.lifecycle.common.libs.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "shipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {
    @Id
    private String id;
    private String orderId;
    private String userId;
    private String shippingAddress;
    private ShipmentStatus status;
    private LocalDateTime preparedAt;
}

package com.order.lifecycle.common.libs.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "shipment_dispatches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentDispatch {
    @Id
    private String id;
    private String orderId;
    private String trackingId;
    private String userId;
    private String logisticsPartner;
    private LocalDateTime dispatchedAt;
}

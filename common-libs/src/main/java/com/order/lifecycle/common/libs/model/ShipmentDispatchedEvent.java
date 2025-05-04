package com.order.lifecycle.common.libs.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDispatchedEvent {
    private String orderId;
    private String userId;
    private String trackingId;
    private String logisticsPartner;
    private LocalDateTime dispatchedAt;
}

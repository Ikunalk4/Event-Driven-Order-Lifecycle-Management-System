package com.order.lifecycle.common.libs.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    private String id;
    private String userId;
    private String eventType;
    private String message;
    private NotificationChannel channel;
    private LocalDateTime sentAt;
    private boolean success;
}

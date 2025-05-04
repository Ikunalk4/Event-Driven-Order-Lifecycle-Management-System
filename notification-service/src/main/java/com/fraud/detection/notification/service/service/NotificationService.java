package com.fraud.detection.notification.service.service;

import com.order.lifecycle.common.libs.model.NotificationChannel;

public interface NotificationService {
    void notifyUser(String userId, String message, NotificationChannel channel, String eventType);
}

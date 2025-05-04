package com.fraud.detection.notification.service.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fraud.detection.notification.service.repo.NotificationRepository;
import com.fraud.detection.notification.service.service.NotificationService;
import com.order.lifecycle.common.libs.model.Notification;
import com.order.lifecycle.common.libs.model.NotificationChannel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
 
    private final NotificationRepository notificationRepository;
 
    @Override
    public void notifyUser(String userId, String message, NotificationChannel channel, String eventType) {
        // Here you would integrate with an actual Email/SMS API like SendGrid, Twilio, etc.
        boolean isSent = true; // simulate success
 
        Notification notification = Notification.builder()
                .userId(userId)
                .message(message)
                .channel(channel)
                .eventType(eventType)
                .sentAt(LocalDateTime.now())
                .success(isSent)
                .build();
 
        notificationRepository.save(notification);
    }
}

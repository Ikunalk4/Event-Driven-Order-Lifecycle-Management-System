package com.fraud.detection.notification.service.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.order.lifecycle.common.libs.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String>{

}

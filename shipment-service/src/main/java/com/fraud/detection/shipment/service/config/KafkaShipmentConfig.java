package com.fraud.detection.shipment.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaShipmentConfig {
	
	@Bean
    public NewTopic shipmentDispatchedEventTopic() {
        return TopicBuilder.name("shipment-dispatched-1")
                .partitions(3) // Adjust based on expected throughput
                .replicas(1)   // Adjust based on your Kafka setup
                .build();
    }
	
	@Bean
    public NewTopic shipmentPreparedEventTopic() {
        return TopicBuilder.name("shipment-prepared-1")
                .partitions(3) // Adjust based on expected throughput
                .replicas(1)   // Adjust based on your Kafka setup
                .build();
    }
	
	@Bean
    public NewTopic shipmentDeliveredEventTopic() {
        return TopicBuilder.name("shipment-delivered-1")
                .partitions(3) // Adjust based on expected throughput
                .replicas(1)   // Adjust based on your Kafka setup
                .build();
    }
}

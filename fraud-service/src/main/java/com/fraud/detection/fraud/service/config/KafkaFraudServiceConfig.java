package com.fraud.detection.fraud.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaFraudServiceConfig {
	
	@Bean
    public NewTopic fraudDetectedEventTopic() {
        return TopicBuilder.name("fraud-detected-1")
                .partitions(3) // Adjust based on expected throughput
                .replicas(1)   // Adjust based on your Kafka setup
                .build();
    }
	
	@Bean
    public NewTopic orderValidatedEventsTopic() {
        return TopicBuilder.name("order-validated-1")
                .partitions(3) // Adjust based on expected throughput
                .replicas(1)   // Adjust based on your Kafka setup
                .build();
    }
}

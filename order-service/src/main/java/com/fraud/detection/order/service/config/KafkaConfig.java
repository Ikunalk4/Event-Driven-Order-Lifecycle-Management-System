package com.fraud.detection.order.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
	
	@Bean
    public NewTopic orderEventsTopic() {
        return TopicBuilder.name("order-created-1")
                .partitions(3) // Adjust based on expected throughput
                .replicas(1)   // Adjust based on your Kafka setup
                .build();
    }
	
	@Bean
    public NewTopic orderCompletedEventsTopic() {
        return TopicBuilder.name("order-completed-1")
                .partitions(3) // Adjust based on expected throughput
                .replicas(1)   // Adjust based on your Kafka setup
                .build();
    }
}

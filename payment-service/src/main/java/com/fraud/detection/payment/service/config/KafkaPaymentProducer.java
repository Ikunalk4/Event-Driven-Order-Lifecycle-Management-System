package com.fraud.detection.payment.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentProducer {
	
	@Bean
    public NewTopic paymentSuccessEventTopic() {
        return TopicBuilder.name("payment-success-2")
                .partitions(3) // Adjust based on expected throughput
                .replicas(1)   // Adjust based on your Kafka setup
                .build();
    }
	
	@Bean
    public NewTopic paymentFailedEventTopic() {
        return TopicBuilder.name("payment-failed-1")
                .partitions(3) // Adjust based on expected throughput
                .replicas(1)   // Adjust based on your Kafka setup
                .build();
    }
}

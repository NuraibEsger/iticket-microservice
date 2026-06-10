package com.iticket.catalog.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    public static final String EVENT_CREATED_TOPIC = "event-created-topic";

    @Bean
    public NewTopic eventCreatedTopic() {
        return TopicBuilder.name(EVENT_CREATED_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}

package com.iticket.catalog.service.messaging;

import com.iticket.catalog.configs.KafkaTopicConfig;
import com.iticket.catalog.model.event.EventCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEventCreatedMessage(EventCreatedEvent event) {
        String key = event.eventId().toString();

        log.info("Sending event-created message to Kafka. EventId: {}", event.eventId());

        CompletableFuture<SendResult<String, Object>> future =
                kafkaTemplate.send(KafkaTopicConfig.EVENT_CREATED_TOPIC,key, event);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message sent successfully to topic: {}, partition: {}, offset: {}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("Failed to send message to Kafka for eventId: {}. Error: {}",
                        event.eventId(), ex.getMessage());
            }
        });
    }
}

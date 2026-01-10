package ru.binarysimple.notification.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private static final String NOTIFICATION_TOPIC = "notifications";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("Sending message to topic {}: {}", NOTIFICATION_TOPIC, message);
        kafkaTemplate.send(NOTIFICATION_TOPIC, message);
    }
}
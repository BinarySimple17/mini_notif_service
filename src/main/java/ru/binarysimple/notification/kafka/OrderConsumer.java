package ru.binarysimple.notification.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.binarysimple.notification.service.NotificationService;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {
    private final NotificationService notificationService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${app.kafka.topics.order-events:notification.events.order}", groupId = "notification-service")
//    public void onOrderEvent(@Payload OrderEvent event) {
    public void onOrderEvent(String message) {

        OrderEvent event = null;
        try {
            event = objectMapper.readValue(message, OrderEvent.class);
            log.info("Received order event {}", event.getParentId());
            notificationService.processOrderEvent(event);

        } catch (JsonProcessingException e) {
            log.error("Failed to process onOrderEvent message: {}", OrderEvent.class, e);
        }

    }
}

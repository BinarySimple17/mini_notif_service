package ru.binarysimple.notification.kafka;

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

    @KafkaListener(topics = "${app.kafka.topics.order-events:order.events}", groupId = "notification-service")
    public void onOrderEvent(@Payload OrderEvent event) {
        log.info("Received order event {}", event.getParentId());
        notificationService.processOrderEvent(event);
    }
}

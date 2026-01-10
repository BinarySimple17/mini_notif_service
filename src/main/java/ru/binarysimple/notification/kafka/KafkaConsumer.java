package ru.binarysimple.notification.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.binarysimple.notification.dto.NotificationDto;
import ru.binarysimple.notification.mapper.NotificationMapper;
import ru.binarysimple.notification.model.Notification;
import ru.binarysimple.notification.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @KafkaListener(topics = "notifications", groupId = "notification-group")
    public void listen(NotificationDto notificationDto) {
        log.info("Received notification from topic 'notifications'");
        
        try {
            // Устанавливаем createdAt в null, чтобы сработал @PrePersist и установился текущее время
            notificationDto = new NotificationDto(
                notificationDto.getNotificationType(),
                null,
                notificationDto.getUsername(),
                notificationDto.getText(),
                notificationDto.getContact()
            );
            
            Notification notification = notificationMapper.toEntity(notificationDto);
            Notification resultNotification = notificationRepository.save(notification);
            
            log.info("Created notification with id: {}", resultNotification.getId());
        } catch (Exception e) {
            log.error("Error processing Kafka message: {}", e.getMessage(), e);
        }
    }
}
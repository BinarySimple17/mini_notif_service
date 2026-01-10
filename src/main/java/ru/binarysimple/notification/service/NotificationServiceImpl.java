package ru.binarysimple.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.binarysimple.notification.dto.NotificationDto;
import ru.binarysimple.notification.kafka.OrderEvent;
import ru.binarysimple.notification.mapper.NotificationMapper;
import ru.binarysimple.notification.model.Notification;
import ru.binarysimple.notification.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public Page<NotificationDto> getAll(Specification<Notification> spec, Pageable pageable) {
        Page<Notification> notifications = notificationRepository.findAll(spec, pageable);
        return notifications.map(notificationMapper::toNotificationDto);
    }

    @Override
    public NotificationDto getOne(Long id) {
        return notificationRepository.findById(id)
                .map(notificationMapper::toNotificationDto)
                .orElseThrow(() -> new RuntimeException("Entity with id `%s` not found".formatted(id)));
    }

    @Override
    public NotificationDto create(NotificationDto dto) {
        Notification notification = notificationMapper.toEntity(dto);
        Notification resultNotification = notificationRepository.save(notification);
        return notificationMapper.toNotificationDto(resultNotification);
    }

    @Override
    public NotificationDto patch(Long id, NotificationDto dto) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity with id `%s` not found".formatted(id)));
        
        notificationMapper.updateWithNull(dto, notification);
        
        Notification resultNotification = notificationRepository.save(notification);
        return notificationMapper.toNotificationDto(resultNotification);
    }

    @Override
    public NotificationDto delete(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification != null) {
            notificationRepository.delete(notification);
        }
        return notificationMapper.toNotificationDto(notification);
    }

    @Override
    public void processOrderEvent(OrderEvent event) {

        Notification notification = notificationMapper.toEntity(event);

//        Notification notification = new Notification();
//        notification.setUsername(event.getUsername());
//        notification.setContact(event.getContact());
////        boolean success = "PAID".equalsIgnoreCase(event.getStatus());
////        notification.setSubject(success ? "Order success" : "Order failed");
//        notification.setText(event.getText());
////        notification.setStatus(event.getStatus());
        notificationRepository.save(notification);
        log.info("Notification processed for user {}", event.getUsername());
    }
}
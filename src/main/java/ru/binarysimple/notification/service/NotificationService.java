package ru.binarysimple.notification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.binarysimple.notification.dto.NotificationDto;
import ru.binarysimple.notification.kafka.OrderEvent;
import ru.binarysimple.notification.model.Notification;

public interface NotificationService {
    
    Page<NotificationDto> getAll(Specification<Notification> spec, Pageable pageable);
    
    NotificationDto getOne(Long id);
    
    NotificationDto create(NotificationDto dto);
    
    NotificationDto patch(Long id, NotificationDto dto);
    
    NotificationDto delete(Long id);

    void processOrderEvent(OrderEvent event);
}
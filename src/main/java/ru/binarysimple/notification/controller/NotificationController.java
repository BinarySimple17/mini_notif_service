package ru.binarysimple.notification.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.binarysimple.notification.filter.NotificationFilter;
import ru.binarysimple.notification.dto.NotificationDto;
import ru.binarysimple.notification.mapper.NotificationMapper;
import ru.binarysimple.notification.model.Notification;
import ru.binarysimple.notification.repository.NotificationRepository;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/notif")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private final ObjectMapper objectMapper;
    
//    private final KafkaProducer kafkaProducer;

    @GetMapping
    public PagedModel<NotificationDto> getAll(@ParameterObject @ModelAttribute NotificationFilter filter, @ParameterObject Pageable pageable) {
        Specification<Notification> spec = filter.toSpecification();
        Page<Notification> notifications = notificationRepository.findAll(spec, pageable);
        Page<NotificationDto> notificationDtoPage = notifications.map(notificationMapper::toNotificationDto);
        return new PagedModel<>(notificationDtoPage);
    }

    @GetMapping("/{id}")
    public NotificationDto getOne(@PathVariable Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        return notificationMapper.toNotificationDto(notificationOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

//    @GetMapping("/by-ids")
//    public List<NotificationDto> getMany(@RequestParam List<Long> ids) {
//        List<Notification> notifications = notificationRepository.findAllById(ids);
//        return notifications.stream()
//                .map(notificationMapper::toNotificationDto)
//                .toList();
//    }

    @PostMapping
    public NotificationDto create(@RequestBody @Valid NotificationDto dto) {
        Notification notification = notificationMapper.toEntity(dto);
        Notification resultNotification = notificationRepository.save(notification);
        
        // Отправляем сообщение в Kafka
//        kafkaProducer.sendMessage(resultNotification.getId().toString());
        
        return notificationMapper.toNotificationDto(resultNotification);
    }

    @PatchMapping("/{id}")
    public NotificationDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        Notification notification = notificationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        NotificationDto notificationDto = notificationMapper.toNotificationDto(notification);
        objectMapper.readerForUpdating(notificationDto).readValue(patchNode);
        notificationMapper.updateWithNull(notificationDto, notification);

        Notification resultNotification = notificationRepository.save(notification);
        return notificationMapper.toNotificationDto(resultNotification);
    }

//    @PatchMapping
//    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
//        Collection<Notification> notifications = notificationRepository.findAllById(ids);
//
//        for (Notification notification : notifications) {
//            NotificationDto notificationDto = notificationMapper.toNotificationDto(notification);
//            objectMapper.readerForUpdating(notificationDto).readValue(patchNode);
//            notificationMapper.updateWithNull(notificationDto, notification);
//        }
//
//        List<Notification> resultNotifications = notificationRepository.saveAll(notifications);
//        return resultNotifications.stream()
//                .map(Notification::getId)
//                .toList();
//    }

    @DeleteMapping("/{id}")
    public NotificationDto delete(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification != null) {
            notificationRepository.delete(notification);
        }
        return notificationMapper.toNotificationDto(notification);
    }

//    @DeleteMapping
//    public void deleteMany(@RequestParam List<Long> ids) {
//        notificationRepository.deleteAllById(ids);
//    }
}

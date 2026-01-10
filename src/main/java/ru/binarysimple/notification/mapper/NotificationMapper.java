package ru.binarysimple.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.binarysimple.notification.dto.NotificationContactDto;
import ru.binarysimple.notification.dto.NotificationDto;
import ru.binarysimple.notification.model.Notification;
import ru.binarysimple.notification.model.NotificationContact;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper {
    Notification toEntity(NotificationDto notificationDto);

    NotificationDto toNotificationDto(Notification notification);

    NotificationContact toEntity(NotificationContactDto contactDto);
    
    NotificationContactDto toDto(NotificationContact contact);

    Notification updateWithNull(NotificationDto notificationDto, @MappingTarget Notification notification);
}
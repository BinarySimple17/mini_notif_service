package ru.binarysimple.notification.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ru.binarysimple.notification.model.NotificationType;
import ru.binarysimple.notification.model.ParentType;

import java.time.LocalDateTime;

/**
 * DTO for {@link ru.binarysimple.notification.model.Notification}
 */
@Value
public class NotificationDto {
    @NotNull
    NotificationType notificationType;
    @NotNull
    ParentType parentType;
    LocalDateTime createdAt;
    @NotNull
    String username;
    @NotNull
    String text;
    @NotNull
    NotificationContactDto contact;
    Long parentId;
}
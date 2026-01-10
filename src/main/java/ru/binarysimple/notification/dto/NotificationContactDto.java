package ru.binarysimple.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

/**
 * DTO for contact information used in notifications
 */
@Value
public class NotificationContactDto {
    @Email
    @NotBlank
    String email;
    
    String phone;
    
    String telegramUsername;
    
    String whatsappUsername;
}
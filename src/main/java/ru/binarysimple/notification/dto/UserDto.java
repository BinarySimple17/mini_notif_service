package ru.binarysimple.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserDto {
    Long id;
    @Size(max = 256)
    String username;
    String firstName;
    String lastName;
    @Email
    String email;
    String phone;
//    UserStatus status;
}
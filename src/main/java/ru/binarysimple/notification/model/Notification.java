package ru.binarysimple.notification.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false, length = 10)
    private NotificationType notificationType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "username")
    private String username;

    @Column(name = "text")
    private String text;

    @Embedded
    private NotificationContact contact;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
package ru.binarysimple.notification.kafka;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.binarysimple.notification.model.NotificationContact;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderEvent {
    private Long orderId;

    private String username;

    private String email;

    private BigDecimal price;

    private String status;

    private String text;

    private NotificationContact contact;
}

package ru.binarysimple.notification.kafka;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.binarysimple.notification.model.NotificationContact;
import ru.binarysimple.notification.model.NotificationType;
import ru.binarysimple.notification.model.OrderStatus;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderEvent {
    private Long orderId;

    private String username;

    private BigDecimal totalCost;

    private OrderStatus status;

//    private String text;

    private NotificationContact contact;

    private NotificationType notificationType;

    private Long parentId;
}

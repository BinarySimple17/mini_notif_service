package ru.binarysimple.notification.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.binarysimple.notification.model.NotificationType;
import ru.binarysimple.notification.model.OrderStatus;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderEvent {
//    private Long orderId;

    private String username;

    private BigDecimal totalCost;

    private OrderStatus status;

//    private String text;

//    private NotificationContact contact;

    private NotificationType notificationType;

    private Long parentId;
}

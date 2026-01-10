package ru.binarysimple.notification.filter;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import ru.binarysimple.notification.model.Notification;
import ru.binarysimple.notification.model.NotificationType;

import java.time.LocalDateTime;

public record NotificationFilter(String username, LocalDateTime createdAtLte, LocalDateTime createdAtGte,
                                 NotificationType notificationType, Long id) {
    public Specification<Notification> toSpecification() {
        return usernameSpec()
                .and(createdAtLteSpec())
                .and(createdAtGteSpec())
                .and(notificationTypeSpec())
                .and(idSpec());
    }

    private Specification<Notification> usernameSpec() {
        return ((root, query, cb) -> StringUtils.hasText(username)
                ? cb.equal(cb.lower(root.get("username")), username.toLowerCase())
                : null);
    }

    private Specification<Notification> createdAtLteSpec() {
        return ((root, query, cb) -> createdAtLte != null
                ? cb.lessThanOrEqualTo(root.get("createdAt"), createdAtLte)
                : null);
    }

    private Specification<Notification> createdAtGteSpec() {
        return ((root, query, cb) -> createdAtGte != null
                ? cb.greaterThanOrEqualTo(root.get("createdAt"), createdAtGte)
                : null);
    }

    private Specification<Notification> notificationTypeSpec() {
        return ((root, query, cb) -> notificationType != null
                ? cb.equal(root.get("notificationType"), notificationType)
                : null);
    }

    private Specification<Notification> idSpec() {
        return ((root, query, cb) -> id != null
                ? cb.equal(root.get("id"), id)
                : null);
    }
}
package ru.binarysimple.notification.filter;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import ru.binarysimple.notification.model.Notification;
import ru.binarysimple.notification.model.NotificationType;
import ru.binarysimple.notification.model.ParentType;

import java.time.LocalDateTime;

public record NotificationFilter(String username, LocalDateTime createdAtLte, LocalDateTime createdAtGte,
                                 NotificationType notificationType, ParentType parentType, Long parentId, Long id) {
    public Specification<Notification> toSpecification() {
        return usernameSpec()
                .and(createdAtLteSpec())
                .and(createdAtGteSpec())
                .and(notificationTypeSpec())
                .and(notificationParentTypeSpec())
                .and(notificationParentIdSpec())
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

    private Specification<Notification> notificationParentTypeSpec() {
        return ((root, query, cb) ->  parentType != null
                ? cb.equal(root.get("parentType"), parentType)
                : null);
    }

    private Specification<Notification> notificationParentIdSpec() {
        return ((root, query, cb) ->  parentId != null
                ? cb.equal(root.get("parentId"), parentId)
                : null);
    }

    private Specification<Notification> idSpec() {
        return ((root, query, cb) -> id != null
                ? cb.equal(root.get("id"), id)
                : null);
    }
}
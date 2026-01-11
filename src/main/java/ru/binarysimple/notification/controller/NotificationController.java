package ru.binarysimple.notification.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;
import ru.binarysimple.notification.dto.NotificationDto;
import ru.binarysimple.notification.filter.NotificationFilter;
import ru.binarysimple.notification.model.Notification;
import ru.binarysimple.notification.service.NotificationService;

import java.io.IOException;

@RestController
@RequestMapping("/notif")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

//    private final ObjectMapper objectMapper;

    @GetMapping
    public PagedModel<NotificationDto> getAll(@ParameterObject @ModelAttribute NotificationFilter filter, @ParameterObject Pageable pageable) {
        Specification<Notification> spec = filter.toSpecification();
        Page<NotificationDto> notificationDtoPage = notificationService.getAll(spec, pageable);
        return new PagedModel<>(notificationDtoPage);
    }

//    @GetMapping("/{id}")
//    public NotificationDto getOne(@PathVariable Long id) {
//        return notificationService.getOne(id);
//    }

    @PostMapping
    public NotificationDto create(@RequestBody @Valid NotificationDto dto) {
        return notificationService.create(dto);
    }

//    @PatchMapping("/{id}")
//    public NotificationDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
//        NotificationDto notificationDto = notificationService.getOne(id);
//        objectMapper.readerForUpdating(notificationDto).readValue(patchNode);
//
//        return notificationService.patch(id, notificationDto);
//    }


//    @DeleteMapping("/{id}")
//    public NotificationDto delete(@PathVariable Long id) {
//        return notificationService.delete(id);
//    }

}

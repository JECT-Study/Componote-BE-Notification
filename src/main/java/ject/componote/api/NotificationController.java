package ject.componote.api;

import ject.componote.application.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notifications")
@RequiredArgsConstructor
@RestController
public class NotificationController {
    private final NotificationService notificationService;

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable final Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.noContent()
                .build();
    }
}

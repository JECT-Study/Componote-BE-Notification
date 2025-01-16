package ject.componote.dto.alert.request;

import ject.componote.domain.Notification;
import ject.componote.domain.NotificationType;
import ject.componote.dto.create.request.NotificationCreateRequest;

public record NotificationAlertRequest(Long notificationId, NotificationType type, String senderNickname, String receiverNickname, String receiverEmail) {
    public static NotificationAlertRequest of(final Notification notification, final NotificationCreateRequest request) {
        return new NotificationAlertRequest(
                notification.getId(),
                notification.getType(),
                request.getSenderNickname(),
                request.getReceiverNickname(),
                request.getReceiverEmail()
        );
    }
}

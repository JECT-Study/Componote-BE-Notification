package ject.componote.api;

import ject.componote.application.NotificationService;
import ject.componote.dto.create.request.impl.CommentLikeNotificationCreateRequest;
import ject.componote.dto.create.request.impl.NestedReplyNotificationCreateRequest;
import ject.componote.dto.create.request.impl.NoticeNotificationCreateRequest;
import ject.componote.dto.create.request.impl.RootReplyNotificationCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@RabbitListener(
        queues = "notificationCreateQueue",
        containerFactory = "rabbitListenerContainerFactory"
)
public class NotificationCreateConsumer {
    private final NotificationService notificationService;

    @RabbitHandler
    public void consumeRootReplyNotification(final RootReplyNotificationCreateRequest request) {
        notificationService.createNotification(request);
    }

    @RabbitHandler
    public void consumeNestedReplyNotification(final NestedReplyNotificationCreateRequest request) {
        notificationService.createNotification(request);
    }

    @RabbitHandler
    public void consumeNoticeNotification(final NoticeNotificationCreateRequest request) {
        notificationService.createNotification(request);
    }

    @RabbitHandler
    public void consumeCommentLikeNotification(final CommentLikeNotificationCreateRequest request) {
        notificationService.createNotification(request);
    }
}

package ject.componote.dto.create.request.impl;

import ject.componote.domain.Notification;
import ject.componote.domain.impl.NestedReplyNotification;
import ject.componote.dto.create.request.NotificationCreateRequest;
import ject.componote.dto.create.request.NotificationParticipant;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class NestedReplyNotificationCreateRequest extends NotificationCreateRequest {
    private final Long commentId;

    public NestedReplyNotificationCreateRequest(final NotificationParticipant participant,
                                                final Long commentId) {
        super(participant);
        this.commentId = commentId;
    }

    @Override
    public Notification toNotification() {
        return NestedReplyNotification.of(
                getSenderId(),
                getReceiverId(),
                commentId
        );
    }
}
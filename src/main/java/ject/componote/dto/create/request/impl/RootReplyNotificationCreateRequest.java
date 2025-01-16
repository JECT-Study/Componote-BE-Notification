package ject.componote.dto.create.request.impl;

import ject.componote.domain.Notification;
import ject.componote.domain.impl.RootReplyNotification;
import ject.componote.dto.create.request.NotificationCreateRequest;
import ject.componote.dto.create.request.NotificationParticipant;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class RootReplyNotificationCreateRequest extends NotificationCreateRequest {
    private final Long commentId;

    public RootReplyNotificationCreateRequest(final NotificationParticipant participant,
                                              final Long commentId) {
        super(participant);
        this.commentId = commentId;
    }

    @Override
    public Notification toNotification() {
        return RootReplyNotification.of(
                getSenderId(),
                getReceiverId(),
                commentId
        );
    }
}
package ject.componote.dto.create.request.impl;

import ject.componote.domain.Notification;
import ject.componote.domain.impl.NoticeNotification;
import ject.componote.dto.create.request.NotificationCreateRequest;
import ject.componote.dto.create.request.NotificationParticipant;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class NoticeNotificationCreateRequest extends NotificationCreateRequest {
    private final Long noticeId;

    public NoticeNotificationCreateRequest(final NotificationParticipant participant,
                                           final Long noticeId) {
        super(participant);
        this.noticeId = noticeId;
    }

    @Override
    public Notification toNotification() {
        return NoticeNotification.of(
                getSenderId(),
                getReceiverId(),
                noticeId
        );
    }
}

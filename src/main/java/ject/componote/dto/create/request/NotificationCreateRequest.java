package ject.componote.dto.create.request;

import ject.componote.domain.Notification;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public abstract class NotificationCreateRequest {
    private final NotificationParticipant participant;

    protected Long getSenderId() {
        return participant.senderId();
    }

    protected Long getReceiverId() {
        return participant.receiverId();
    }

    public String getSenderNickname() {
        return participant.senderNickname();
    }

    public String getReceiverNickname() {
        return participant.receiverNickname();
    }

    public String getReceiverEmail() {
        return participant.receiverEmail();
    }

    public abstract Notification toNotification();
}

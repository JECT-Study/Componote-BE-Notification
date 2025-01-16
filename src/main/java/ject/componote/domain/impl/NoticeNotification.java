package ject.componote.domain.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import ject.componote.domain.Notification;
import ject.componote.domain.NotificationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class NoticeNotification extends Notification {
    @Column(name = "notice_id", nullable = false)
    private Long noticeId;

    private NoticeNotification(final Long senderId, final Long receiverId, final Long noticeId) {
        super(senderId, receiverId, NotificationType.NOTICE);
        this.noticeId = noticeId;
    }

    public static NoticeNotification of(final Long senderId, final Long receiverId, final Long commentLikeId) {
        return new NoticeNotification(senderId, receiverId, commentLikeId);
    }
}

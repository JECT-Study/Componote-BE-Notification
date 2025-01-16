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
public class RootReplyNotification extends Notification {
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    private RootReplyNotification(final Long senderId, final Long receiverId, final Long commentId) {
        super(senderId, receiverId, NotificationType.ROOT_REPLY);
        this.commentId = commentId;
    }

    public static RootReplyNotification of(final Long senderId, final Long receiverId, final Long commentId) {
        return new RootReplyNotification(senderId, receiverId, commentId);
    }
}

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
public class NestedReplyNotification extends Notification {
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    private NestedReplyNotification(final Long senderId, final Long receiverId, final Long commentId) {
        super(senderId, receiverId, NotificationType.NESTED_REPLY);
        this.commentId = commentId;
    }

    public static NestedReplyNotification of(final Long senderId, final Long receiverId, final Long commentId) {
        return new NestedReplyNotification(senderId, receiverId, commentId);
    }
}

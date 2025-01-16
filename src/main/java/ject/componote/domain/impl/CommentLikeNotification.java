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
public class CommentLikeNotification extends Notification {
    @Column(name = "comment_like_id", nullable = false)
    private Long commentLikeId;

    private CommentLikeNotification(final Long senderId, final Long receiverId, final Long commentLikeId) {
        super(senderId, receiverId, NotificationType.COMMENT_LIKE);
        this.commentLikeId = commentLikeId;
    }

    public static CommentLikeNotification of(final Long senderId, final Long receiverId, final Long commentLikeId) {
        return new CommentLikeNotification(senderId, receiverId, commentLikeId);
    }
}

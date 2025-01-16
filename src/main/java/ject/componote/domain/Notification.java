package ject.componote.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE componote_notification.notification SET disable = true WHERE id = ?")
@SQLRestriction("disable = false")
@ToString
public abstract class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name = "disable", nullable = false)
    private Boolean disable;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected Notification(final Long senderId, final Long receiverId, final NotificationType type) {
        this.isRead = false;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.type = type;
        this.disable = Boolean.FALSE;
        this.createdAt = LocalDateTime.now();
    }
}

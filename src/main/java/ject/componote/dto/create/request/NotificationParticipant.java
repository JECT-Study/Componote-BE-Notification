package ject.componote.dto.create.request;

public record NotificationParticipant(
        Long senderId,
        String senderNickname,
        Long receiverId,
        String receiverNickname,
        String receiverEmail
) {
}

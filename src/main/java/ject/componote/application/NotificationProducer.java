package ject.componote.application;

import ject.componote.dto.alert.request.NotificationAlertRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendAll(final NotificationAlertRequest message) {
        sendEmail(message);
        sendWebPush(message);
    }

    public void sendEmail(final NotificationAlertRequest message) {
        rabbitTemplate.convertAndSend("notification-mail", "email", message);
    }

    public void sendWebPush(final NotificationAlertRequest message) {
        rabbitTemplate.convertAndSend("notification-fcm", "fcm", message);
    }
}

package ject.componote.application;

import ject.componote.dao.NotificationRepository;
import ject.componote.domain.Notification;
import ject.componote.dto.create.request.NotificationCreateRequest;
import ject.componote.dto.alert.request.NotificationAlertRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationProducer notificationProducer;

    @Transactional
    public void createNotification(final NotificationCreateRequest request) {
        final Notification notification = notificationRepository.save(request.toNotification());
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                final NotificationAlertRequest message = NotificationAlertRequest.of(notification, request);
                notificationProducer.sendAll(message);
            }
        });
    }

    @Transactional
    public void deleteNotification(final Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }
}

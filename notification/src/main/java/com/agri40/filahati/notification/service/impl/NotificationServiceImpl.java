package com.agri40.filahati.notification.service.impl;

import com.agri40.filahati.notification.domain.Notification;
import com.agri40.filahati.notification.repository.NotificationRepository;
import com.agri40.filahati.notification.service.NotificationService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Notification}.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification save(Notification notification) {
        log.debug("Request to save Notification : {}", notification);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification update(Notification notification) {
        log.debug("Request to update Notification : {}", notification);
        return notificationRepository.save(notification);
    }

    @Override
    public Optional<Notification> partialUpdate(Notification notification) {
        log.debug("Request to partially update Notification : {}", notification);

        return notificationRepository
            .findById(notification.getId())
            .map(existingNotification -> {
                if (notification.getDescription() != null) {
                    existingNotification.setDescription(notification.getDescription());
                }
                if (notification.getRucheName() != null) {
                    existingNotification.setRucheName(notification.getRucheName());
                }
                if (notification.getRucherName() != null) {
                    existingNotification.setRucherName(notification.getRucherName());
                }
                if (notification.getCreatedAt() != null) {
                    existingNotification.setCreatedAt(notification.getCreatedAt());
                }

                return existingNotification;
            })
            .map(notificationRepository::save);
    }

    @Override
    public List<Notification> findAll() {
        log.debug("Request to get all Notifications");
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findOne(String id) {
        log.debug("Request to get Notification : {}", id);
        return notificationRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Notification : {}", id);
        notificationRepository.deleteById(id);
    }
}

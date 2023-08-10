package com.agri40.filahati.notification.service.impl;

import com.agri40.filahati.notification.domain.Alert;
import com.agri40.filahati.notification.repository.AlertRepository;
import com.agri40.filahati.notification.service.AlertService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Alert}.
 */
@Service
public class AlertServiceImpl implements AlertService {

    private final Logger log = LoggerFactory.getLogger(AlertServiceImpl.class);

    private final AlertRepository alertRepository;

    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public Alert save(Alert alert) {
        log.debug("Request to save Alert : {}", alert);
        return alertRepository.save(alert);
    }

    @Override
    public Alert update(Alert alert) {
        log.debug("Request to update Alert : {}", alert);
        return alertRepository.save(alert);
    }

    @Override
    public Optional<Alert> partialUpdate(Alert alert) {
        log.debug("Request to partially update Alert : {}", alert);

        return alertRepository
            .findById(alert.getId())
            .map(existingAlert -> {
                if (alert.getType() != null) {
                    existingAlert.setType(alert.getType());
                }
                if (alert.getAlertSi() != null) {
                    existingAlert.setAlertSi(alert.getAlertSi());
                }
                if (alert.getConditionType() != null) {
                    existingAlert.setConditionType(alert.getConditionType());
                }
                if (alert.getValeur() != null) {
                    existingAlert.setValeur(alert.getValeur());
                }
                if (alert.getVariation() != null) {
                    existingAlert.setVariation(alert.getVariation());
                }
                if (alert.getStatut() != null) {
                    existingAlert.setStatut(alert.getStatut());
                }
                if (alert.getRucheId() != null) {
                    existingAlert.setRucheId(alert.getRucheId());
                }
                if (alert.getLastStream() != null) {
                    existingAlert.setLastStream(alert.getLastStream());
                }

                return existingAlert;
            })
            .map(alertRepository::save);
    }

    @Override
    public List<Alert> findAll() {
        log.debug("Request to get all Alerts");
        return alertRepository.findAll();
    }

    @Override
    public Optional<Alert> findOne(String id) {
        log.debug("Request to get Alert : {}", id);
        return alertRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Alert : {}", id);
        alertRepository.deleteById(id);
    }
}

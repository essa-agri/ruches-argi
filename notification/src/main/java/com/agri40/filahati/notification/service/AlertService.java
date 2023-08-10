package com.agri40.filahati.notification.service;

import com.agri40.filahati.notification.domain.Alert;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Alert}.
 */
public interface AlertService {
    /**
     * Save a alert.
     *
     * @param alert the entity to save.
     * @return the persisted entity.
     */
    Alert save(Alert alert);

    /**
     * Updates a alert.
     *
     * @param alert the entity to update.
     * @return the persisted entity.
     */
    Alert update(Alert alert);

    /**
     * Partially updates a alert.
     *
     * @param alert the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Alert> partialUpdate(Alert alert);

    /**
     * Get all the alerts.
     *
     * @return the list of entities.
     */
    List<Alert> findAll();

    /**
     * Get the "id" alert.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Alert> findOne(String id);

    /**
     * Delete the "id" alert.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}

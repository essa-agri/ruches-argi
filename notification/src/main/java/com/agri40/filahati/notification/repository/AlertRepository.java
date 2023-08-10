package com.agri40.filahati.notification.repository;

import com.agri40.filahati.notification.domain.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Alert entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlertRepository extends MongoRepository<Alert, String> {}

package com.agri40.filahati.sensoring.repository;

import com.agri40.filahati.sensoring.domain.Stream;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Stream entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StreamRepository extends MongoRepository<Stream, String> {}

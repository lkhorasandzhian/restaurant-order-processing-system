package com.kpo.rops.repository;

import com.kpo.rops.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Special repository for working with 'SESSION' database.
 */
public interface SessionRepository extends JpaRepository<Session, Integer> {
}

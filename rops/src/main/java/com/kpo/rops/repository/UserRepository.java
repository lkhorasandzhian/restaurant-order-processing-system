package com.kpo.rops.repository;

import java.util.Optional;

import com.kpo.rops.models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kpo.rops.models.User;

/**
 * Special repository for working with 'USERS' database.
 * There are abilities to find user by username, email.
 * Also, you can just check existence by username, email and role.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByRole(ERole role);
}

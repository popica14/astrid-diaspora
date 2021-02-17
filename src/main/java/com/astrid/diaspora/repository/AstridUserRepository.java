package com.astrid.diaspora.repository;

import java.util.Optional;

import com.astrid.diaspora.domain.AstridUser;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AstridUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AstridUserRepository extends JpaRepository<AstridUser, Long> {
   Optional<AstridUser> findByUserId(Long id);
}

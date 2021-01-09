package com.astrid.diaspora.repository;

import com.astrid.diaspora.domain.EntityCreation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the EntityCreation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityCreationRepository extends JpaRepository<EntityCreation, Long> {

    @Query("select entityCreation from EntityCreation entityCreation where entityCreation.createdBy.login = ?#{principal.username}")
    List<EntityCreation> findByCreatedByIsCurrentUser();
}

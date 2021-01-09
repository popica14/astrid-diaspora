package com.astrid.diaspora.repository;

import com.astrid.diaspora.domain.EntityLastModification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the EntityLastModification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityLastModificationRepository extends JpaRepository<EntityLastModification, Long> {

    @Query("select entityLastModification from EntityLastModification entityLastModification where entityLastModification.lastModifiedBy.login = ?#{principal.username}")
    List<EntityLastModification> findByLastModifiedByIsCurrentUser();
}

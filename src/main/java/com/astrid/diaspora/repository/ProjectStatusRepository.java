package com.astrid.diaspora.repository;

import com.astrid.diaspora.domain.ProjectStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long> {
    ProjectStatus findByOrder(int i);
}

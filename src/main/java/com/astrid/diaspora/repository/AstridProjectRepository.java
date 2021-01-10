package com.astrid.diaspora.repository;

import com.astrid.diaspora.domain.AstridProject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the AstridProject entity.
 */
@Repository
public interface AstridProjectRepository extends JpaRepository<AstridProject, Long> {

    @Query("select astridProject from AstridProject astridProject where astridProject.responsible.login = ?#{principal.username}")
    List<AstridProject> findByResponsibleIsCurrentUser();

    @Query("select astridProject from AstridProject astridProject where astridProject.initiator.login = ?#{principal.username}")
    List<AstridProject> findByInitiatorIsCurrentUser();

    @Query(value = "select distinct astridProject from AstridProject astridProject left join fetch astridProject.implementationTeams left join fetch astridProject.beneficiaries",
        countQuery = "select count(distinct astridProject) from AstridProject astridProject")
    Page<AstridProject> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct astridProject from AstridProject astridProject left join fetch astridProject.implementationTeams left join fetch astridProject.beneficiaries")
    List<AstridProject> findAllWithEagerRelationships();

    @Query("select astridProject from AstridProject astridProject left join fetch astridProject.implementationTeams left join fetch astridProject.beneficiaries where astridProject.id =:id")
    Optional<AstridProject> findOneWithEagerRelationships(@Param("id") Long id);
}

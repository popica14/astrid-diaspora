package com.astrid.diaspora.repository;

import com.astrid.diaspora.domain.AstridProjectSuggestion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the AstridProjectSuggestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AstridProjectSuggestionRepository extends JpaRepository<AstridProjectSuggestion, Long> {

    @Query("select astridProjectSuggestion from AstridProjectSuggestion astridProjectSuggestion where astridProjectSuggestion.initiator.login = ?#{principal.username}")
    List<AstridProjectSuggestion> findByInitiatorIsCurrentUser();
}

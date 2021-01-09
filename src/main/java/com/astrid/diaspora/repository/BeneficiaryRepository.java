package com.astrid.diaspora.repository;

import com.astrid.diaspora.domain.Beneficiary;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Beneficiary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    @Query("select beneficiary from Beneficiary beneficiary where beneficiary.contactPerson.login = ?#{principal.username}")
    List<Beneficiary> findByContactPersonIsCurrentUser();
}

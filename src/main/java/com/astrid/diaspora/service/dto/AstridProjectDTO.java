package com.astrid.diaspora.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.astrid.diaspora.domain.AstridProject} entity.
 */
public class AstridProjectDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    
    @Lob
    private String shortDescription;

    @Lob
    private byte[] documentation;

    private String documentationContentType;
    private String neededAmount;

    private String currentAmount;

    private String currency;

    private Integer supporters;

    private String goal;

    private String statusReason;

    private ZonedDateTime statusDeadline;


    private Long entityCreationId;

    private Long entityLastModificationId;

    private Long responsibleId;

    private String responsibleLogin;

    private Long initiatorId;

    private String initiatorLogin;

    private Long statusId;

    private String statusName;

    private Long locationId;

    private String locationName;
    private Set<UserDTO> implementationTeams = new HashSet<>();
    private Set<BeneficiaryDTO> beneficiaries = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public byte[] getDocumentation() {
        return documentation;
    }

    public void setDocumentation(byte[] documentation) {
        this.documentation = documentation;
    }

    public String getDocumentationContentType() {
        return documentationContentType;
    }

    public void setDocumentationContentType(String documentationContentType) {
        this.documentationContentType = documentationContentType;
    }

    public String getNeededAmount() {
        return neededAmount;
    }

    public void setNeededAmount(String neededAmount) {
        this.neededAmount = neededAmount;
    }

    public String getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(String currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getSupporters() {
        return supporters;
    }

    public void setSupporters(Integer supporters) {
        this.supporters = supporters;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public ZonedDateTime getStatusDeadline() {
        return statusDeadline;
    }

    public void setStatusDeadline(ZonedDateTime statusDeadline) {
        this.statusDeadline = statusDeadline;
    }

    public Long getEntityCreationId() {
        return entityCreationId;
    }

    public void setEntityCreationId(Long entityCreationId) {
        this.entityCreationId = entityCreationId;
    }

    public Long getEntityLastModificationId() {
        return entityLastModificationId;
    }

    public void setEntityLastModificationId(Long entityLastModificationId) {
        this.entityLastModificationId = entityLastModificationId;
    }

    public Long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(Long userId) {
        this.responsibleId = userId;
    }

    public String getResponsibleLogin() {
        return responsibleLogin;
    }

    public void setResponsibleLogin(String userLogin) {
        this.responsibleLogin = userLogin;
    }

    public Long getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(Long userId) {
        this.initiatorId = userId;
    }

    public String getInitiatorLogin() {
        return initiatorLogin;
    }

    public void setInitiatorLogin(String userLogin) {
        this.initiatorLogin = userLogin;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long projectStatusId) {
        this.statusId = projectStatusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String projectStatusName) {
        this.statusName = projectStatusName;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<UserDTO> getImplementationTeams() {
        return implementationTeams;
    }

    public void setImplementationTeams(Set<UserDTO> users) {
        this.implementationTeams = users;
    }

    public Set<BeneficiaryDTO> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(Set<BeneficiaryDTO> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AstridProjectDTO)) {
            return false;
        }

        return id != null && id.equals(((AstridProjectDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AstridProjectDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortDescription='" + getShortDescription() + "'" +
            ", documentation='" + getDocumentation() + "'" +
            ", neededAmount='" + getNeededAmount() + "'" +
            ", currentAmount='" + getCurrentAmount() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", supporters=" + getSupporters() +
            ", goal='" + getGoal() + "'" +
            ", statusReason='" + getStatusReason() + "'" +
            ", statusDeadline='" + getStatusDeadline() + "'" +
            ", entityCreationId=" + getEntityCreationId() +
            ", entityLastModificationId=" + getEntityLastModificationId() +
            ", responsibleId=" + getResponsibleId() +
            ", responsibleLogin='" + getResponsibleLogin() + "'" +
            ", initiatorId=" + getInitiatorId() +
            ", initiatorLogin='" + getInitiatorLogin() + "'" +
            ", statusId=" + getStatusId() +
            ", statusName='" + getStatusName() + "'" +
            ", locationId=" + getLocationId() +
            ", locationName='" + getLocationName() + "'" +
            ", implementationTeams='" + getImplementationTeams() + "'" +
            ", beneficiaries='" + getBeneficiaries() + "'" +
            "}";
    }
}

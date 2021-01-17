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

    private String neededAmount;

    private String currentAmount;

    private Integer supporters;

    @NotNull
    private String goal;

    @NotNull
    private String statusReason;

    private ZonedDateTime statusDeadline;

    @Lob
    private byte[] documentation1;

    private String documentation1ContentType;
    @Lob
    private byte[] documentation2;

    private String documentation2ContentType;
    @Lob
    private byte[] documentation3;

    private String documentation3ContentType;
    @Lob
    private byte[] documentation4;

    private String documentation4ContentType;
    @Lob
    private byte[] documentation5;

    private String documentation5ContentType;

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

    private Long currencyId;

    private String currencyName;
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

    public byte[] getDocumentation1() {
        return documentation1;
    }

    public void setDocumentation1(byte[] documentation1) {
        this.documentation1 = documentation1;
    }

    public String getDocumentation1ContentType() {
        return documentation1ContentType;
    }

    public void setDocumentation1ContentType(String documentation1ContentType) {
        this.documentation1ContentType = documentation1ContentType;
    }

    public byte[] getDocumentation2() {
        return documentation2;
    }

    public void setDocumentation2(byte[] documentation2) {
        this.documentation2 = documentation2;
    }

    public String getDocumentation2ContentType() {
        return documentation2ContentType;
    }

    public void setDocumentation2ContentType(String documentation2ContentType) {
        this.documentation2ContentType = documentation2ContentType;
    }

    public byte[] getDocumentation3() {
        return documentation3;
    }

    public void setDocumentation3(byte[] documentation3) {
        this.documentation3 = documentation3;
    }

    public String getDocumentation3ContentType() {
        return documentation3ContentType;
    }

    public void setDocumentation3ContentType(String documentation3ContentType) {
        this.documentation3ContentType = documentation3ContentType;
    }

    public byte[] getDocumentation4() {
        return documentation4;
    }

    public void setDocumentation4(byte[] documentation4) {
        this.documentation4 = documentation4;
    }

    public String getDocumentation4ContentType() {
        return documentation4ContentType;
    }

    public void setDocumentation4ContentType(String documentation4ContentType) {
        this.documentation4ContentType = documentation4ContentType;
    }

    public byte[] getDocumentation5() {
        return documentation5;
    }

    public void setDocumentation5(byte[] documentation5) {
        this.documentation5 = documentation5;
    }

    public String getDocumentation5ContentType() {
        return documentation5ContentType;
    }

    public void setDocumentation5ContentType(String documentation5ContentType) {
        this.documentation5ContentType = documentation5ContentType;
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

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
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
            ", neededAmount='" + getNeededAmount() + "'" +
            ", currentAmount='" + getCurrentAmount() + "'" +
            ", supporters=" + getSupporters() +
            ", goal='" + getGoal() + "'" +
            ", statusReason='" + getStatusReason() + "'" +
            ", statusDeadline='" + getStatusDeadline() + "'" +
            ", documentation1='" + getDocumentation1() + "'" +
            ", documentation2='" + getDocumentation2() + "'" +
            ", documentation3='" + getDocumentation3() + "'" +
            ", documentation4='" + getDocumentation4() + "'" +
            ", documentation5='" + getDocumentation5() + "'" +
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
            ", currencyId=" + getCurrencyId() +
            ", currencyName='" + getCurrencyName() + "'" +
            ", implementationTeams='" + getImplementationTeams() + "'" +
            ", beneficiaries='" + getBeneficiaries() + "'" +
            "}";
    }
}

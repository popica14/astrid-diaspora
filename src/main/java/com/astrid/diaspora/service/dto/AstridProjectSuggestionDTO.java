package com.astrid.diaspora.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.astrid.diaspora.domain.AstridProjectSuggestion} entity.
 */
public class AstridProjectSuggestionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    
    @Lob
    private String shortDescription;

    @Lob
    private byte[] documentation;

    private String documentationContentType;
    private String goal;


    private Long initiatorId;

    private String initiatorLogin;

    private Long statusId;

    private String statusName;
    
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

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AstridProjectSuggestionDTO)) {
            return false;
        }

        return id != null && id.equals(((AstridProjectSuggestionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AstridProjectSuggestionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortDescription='" + getShortDescription() + "'" +
            ", documentation='" + getDocumentation() + "'" +
            ", goal='" + getGoal() + "'" +
            ", initiatorId=" + getInitiatorId() +
            ", initiatorLogin='" + getInitiatorLogin() + "'" +
            ", statusId=" + getStatusId() +
            ", statusName='" + getStatusName() + "'" +
            "}";
    }
}

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
    @NotNull
    private String goal;

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
            ", documentation1='" + getDocumentation1() + "'" +
            ", documentation2='" + getDocumentation2() + "'" +
            ", documentation3='" + getDocumentation3() + "'" +
            ", documentation4='" + getDocumentation4() + "'" +
            ", documentation5='" + getDocumentation5() + "'" +
            ", initiatorId=" + getInitiatorId() +
            ", initiatorLogin='" + getInitiatorLogin() + "'" +
            ", statusId=" + getStatusId() +
            ", statusName='" + getStatusName() + "'" +
            "}";
    }
}

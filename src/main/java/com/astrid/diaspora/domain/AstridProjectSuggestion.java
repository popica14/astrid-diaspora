package com.astrid.diaspora.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A AstridProjectSuggestion.
 */
@Entity
@Table(name = "astrid_project_suggestion")
public class AstridProjectSuggestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Lob
    @Column(name = "documentation")
    private byte[] documentation;

    @Column(name = "documentation_content_type")
    private String documentationContentType;

    @NotNull
    @Column(name = "goal", nullable = false)
    private String goal;

    @Lob
    @Column(name = "documentation_1")
    private byte[] documentation1;

    @Column(name = "documentation_1_content_type")
    private String documentation1ContentType;

    @Lob
    @Column(name = "documentation_2")
    private byte[] documentation2;

    @Column(name = "documentation_2_content_type")
    private String documentation2ContentType;

    @Lob
    @Column(name = "documentation_3")
    private byte[] documentation3;

    @Column(name = "documentation_3_content_type")
    private String documentation3ContentType;

    @Lob
    @Column(name = "documentation_4")
    private byte[] documentation4;

    @Column(name = "documentation_4_content_type")
    private String documentation4ContentType;

    @Lob
    @Column(name = "documentation_5")
    private byte[] documentation5;

    @Column(name = "documentation_5_content_type")
    private String documentation5ContentType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "astridProjectSuggestions", allowSetters = true)
    private User initiator;

    @ManyToOne
    @JsonIgnoreProperties(value = "astridProjectSuggestions", allowSetters = true)
    private ProjectStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AstridProjectSuggestion name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public AstridProjectSuggestion shortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public byte[] getDocumentation() {
        return documentation;
    }

    public AstridProjectSuggestion documentation(byte[] documentation) {
        this.documentation = documentation;
        return this;
    }

    public void setDocumentation(byte[] documentation) {
        this.documentation = documentation;
    }

    public String getDocumentationContentType() {
        return documentationContentType;
    }

    public AstridProjectSuggestion documentationContentType(String documentationContentType) {
        this.documentationContentType = documentationContentType;
        return this;
    }

    public void setDocumentationContentType(String documentationContentType) {
        this.documentationContentType = documentationContentType;
    }

    public String getGoal() {
        return goal;
    }

    public AstridProjectSuggestion goal(String goal) {
        this.goal = goal;
        return this;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public byte[] getDocumentation1() {
        return documentation1;
    }

    public AstridProjectSuggestion documentation1(byte[] documentation1) {
        this.documentation1 = documentation1;
        return this;
    }

    public void setDocumentation1(byte[] documentation1) {
        this.documentation1 = documentation1;
    }

    public String getDocumentation1ContentType() {
        return documentation1ContentType;
    }

    public AstridProjectSuggestion documentation1ContentType(String documentation1ContentType) {
        this.documentation1ContentType = documentation1ContentType;
        return this;
    }

    public void setDocumentation1ContentType(String documentation1ContentType) {
        this.documentation1ContentType = documentation1ContentType;
    }

    public byte[] getDocumentation2() {
        return documentation2;
    }

    public AstridProjectSuggestion documentation2(byte[] documentation2) {
        this.documentation2 = documentation2;
        return this;
    }

    public void setDocumentation2(byte[] documentation2) {
        this.documentation2 = documentation2;
    }

    public String getDocumentation2ContentType() {
        return documentation2ContentType;
    }

    public AstridProjectSuggestion documentation2ContentType(String documentation2ContentType) {
        this.documentation2ContentType = documentation2ContentType;
        return this;
    }

    public void setDocumentation2ContentType(String documentation2ContentType) {
        this.documentation2ContentType = documentation2ContentType;
    }

    public byte[] getDocumentation3() {
        return documentation3;
    }

    public AstridProjectSuggestion documentation3(byte[] documentation3) {
        this.documentation3 = documentation3;
        return this;
    }

    public void setDocumentation3(byte[] documentation3) {
        this.documentation3 = documentation3;
    }

    public String getDocumentation3ContentType() {
        return documentation3ContentType;
    }

    public AstridProjectSuggestion documentation3ContentType(String documentation3ContentType) {
        this.documentation3ContentType = documentation3ContentType;
        return this;
    }

    public void setDocumentation3ContentType(String documentation3ContentType) {
        this.documentation3ContentType = documentation3ContentType;
    }

    public byte[] getDocumentation4() {
        return documentation4;
    }

    public AstridProjectSuggestion documentation4(byte[] documentation4) {
        this.documentation4 = documentation4;
        return this;
    }

    public void setDocumentation4(byte[] documentation4) {
        this.documentation4 = documentation4;
    }

    public String getDocumentation4ContentType() {
        return documentation4ContentType;
    }

    public AstridProjectSuggestion documentation4ContentType(String documentation4ContentType) {
        this.documentation4ContentType = documentation4ContentType;
        return this;
    }

    public void setDocumentation4ContentType(String documentation4ContentType) {
        this.documentation4ContentType = documentation4ContentType;
    }

    public byte[] getDocumentation5() {
        return documentation5;
    }

    public AstridProjectSuggestion documentation5(byte[] documentation5) {
        this.documentation5 = documentation5;
        return this;
    }

    public void setDocumentation5(byte[] documentation5) {
        this.documentation5 = documentation5;
    }

    public String getDocumentation5ContentType() {
        return documentation5ContentType;
    }

    public AstridProjectSuggestion documentation5ContentType(String documentation5ContentType) {
        this.documentation5ContentType = documentation5ContentType;
        return this;
    }

    public void setDocumentation5ContentType(String documentation5ContentType) {
        this.documentation5ContentType = documentation5ContentType;
    }

    public User getInitiator() {
        return initiator;
    }

    public AstridProjectSuggestion initiator(User user) {
        this.initiator = user;
        return this;
    }

    public void setInitiator(User user) {
        this.initiator = user;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public AstridProjectSuggestion status(ProjectStatus projectStatus) {
        this.status = projectStatus;
        return this;
    }

    public void setStatus(ProjectStatus projectStatus) {
        this.status = projectStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AstridProjectSuggestion)) {
            return false;
        }
        return id != null && id.equals(((AstridProjectSuggestion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AstridProjectSuggestion{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortDescription='" + getShortDescription() + "'" +
            ", documentation='" + getDocumentation() + "'" +
            ", documentationContentType='" + getDocumentationContentType() + "'" +
            ", goal='" + getGoal() + "'" +
            ", documentation1='" + getDocumentation1() + "'" +
            ", documentation1ContentType='" + getDocumentation1ContentType() + "'" +
            ", documentation2='" + getDocumentation2() + "'" +
            ", documentation2ContentType='" + getDocumentation2ContentType() + "'" +
            ", documentation3='" + getDocumentation3() + "'" +
            ", documentation3ContentType='" + getDocumentation3ContentType() + "'" +
            ", documentation4='" + getDocumentation4() + "'" +
            ", documentation4ContentType='" + getDocumentation4ContentType() + "'" +
            ", documentation5='" + getDocumentation5() + "'" +
            ", documentation5ContentType='" + getDocumentation5ContentType() + "'" +
            "}";
    }
}

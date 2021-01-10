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

    @Column(name = "goal")
    private String goal;

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
            "}";
    }
}

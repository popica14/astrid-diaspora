package com.astrid.diaspora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A EntityLastModification.
 */
@Entity
@Table(name = "entity_last_modification")
public class EntityLastModification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_modified")
    private ZonedDateTime lastModified;

    @ManyToOne
    @JsonIgnoreProperties(value = "entityLastModifications", allowSetters = true)
    private User lastModifiedBy;

    @OneToOne(mappedBy = "entityLastModification")
    @JsonIgnore
    private AstridProject astridProject;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public EntityLastModification lastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public EntityLastModification lastModifiedBy(User user) {
        this.lastModifiedBy = user;
        return this;
    }

    public void setLastModifiedBy(User user) {
        this.lastModifiedBy = user;
    }

    public AstridProject getAstridProject() {
        return astridProject;
    }

    public EntityLastModification astridProject(AstridProject astridProject) {
        this.astridProject = astridProject;
        return this;
    }

    public void setAstridProject(AstridProject astridProject) {
        this.astridProject = astridProject;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityLastModification)) {
            return false;
        }
        return id != null && id.equals(((EntityLastModification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityLastModification{" +
            "id=" + getId() +
            ", lastModified='" + getLastModified() + "'" +
            "}";
    }
}

package com.astrid.diaspora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A EntityCreation.
 */
@Entity
@Table(name = "entity_creation")
public class EntityCreation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "created", nullable = false)
    private ZonedDateTime created;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "entityCreations", allowSetters = true)
    private User createdBy;

    @OneToOne(mappedBy = "entityCreation")
    @JsonIgnore
    private AstridProject astridProject;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public EntityCreation created(ZonedDateTime created) {
        this.created = created;
        return this;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public EntityCreation createdBy(User user) {
        this.createdBy = user;
        return this;
    }

    public void setCreatedBy(User user) {
        this.createdBy = user;
    }

    public AstridProject getAstridProject() {
        return astridProject;
    }

    public EntityCreation astridProject(AstridProject astridProject) {
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
        if (!(o instanceof EntityCreation)) {
            return false;
        }
        return id != null && id.equals(((EntityCreation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCreation{" +
            "id=" + getId() +
            ", created='" + getCreated() + "'" +
            "}";
    }
}

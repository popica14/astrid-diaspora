package com.astrid.diaspora.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A AstridProject.
 */
@Entity
@Table(name = "astrid_project")
public class AstridProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties(value = "astridProjects", allowSetters = true)
    private User responsible;

    @ManyToMany
    @JoinTable(name = "astrid_project_implementation_team",
               joinColumns = @JoinColumn(name = "astrid_project_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "implementation_team_id", referencedColumnName = "id"))
    private Set<User> implementationTeams = new HashSet<>();

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

    public AstridProject name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public AstridProject description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getResponsible() {
        return responsible;
    }

    public AstridProject responsible(User user) {
        this.responsible = user;
        return this;
    }

    public void setResponsible(User user) {
        this.responsible = user;
    }

    public Set<User> getImplementationTeams() {
        return implementationTeams;
    }

    public AstridProject implementationTeams(Set<User> users) {
        this.implementationTeams = users;
        return this;
    }

    public AstridProject addImplementationTeam(User user) {
        this.implementationTeams.add(user);
        return this;
    }

    public AstridProject removeImplementationTeam(User user) {
        this.implementationTeams.remove(user);
        return this;
    }

    public void setImplementationTeams(Set<User> users) {
        this.implementationTeams = users;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AstridProject)) {
            return false;
        }
        return id != null && id.equals(((AstridProject) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AstridProject{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

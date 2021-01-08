package com.astrid.diaspora.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.astrid.diaspora.domain.AstridProject} entity.
 */
public class AstridProjectDTO implements Serializable {
    
    private Long id;

    private String name;

    @Lob
    private String description;


    private Long responsibleId;

    private String responsibleLogin;
    private Set<UserDTO> implementationTeams = new HashSet<>();
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<UserDTO> getImplementationTeams() {
        return implementationTeams;
    }

    public void setImplementationTeams(Set<UserDTO> users) {
        this.implementationTeams = users;
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
            ", description='" + getDescription() + "'" +
            ", responsibleId=" + getResponsibleId() +
            ", responsibleLogin='" + getResponsibleLogin() + "'" +
            ", implementationTeams='" + getImplementationTeams() + "'" +
            "}";
    }
}

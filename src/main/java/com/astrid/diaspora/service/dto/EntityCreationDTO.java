package com.astrid.diaspora.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.astrid.diaspora.domain.EntityCreation} entity.
 */
public class EntityCreationDTO implements Serializable {
    
    private Long id;

    @NotNull
    private ZonedDateTime created;


    private Long createdById;

    private String createdByLogin;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long userId) {
        this.createdById = userId;
    }

    public String getCreatedByLogin() {
        return createdByLogin;
    }

    public void setCreatedByLogin(String userLogin) {
        this.createdByLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityCreationDTO)) {
            return false;
        }

        return id != null && id.equals(((EntityCreationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityCreationDTO{" +
            "id=" + getId() +
            ", created='" + getCreated() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByLogin='" + getCreatedByLogin() + "'" +
            "}";
    }
}

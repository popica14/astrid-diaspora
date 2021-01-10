package com.astrid.diaspora.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.astrid.diaspora.domain.EntityLastModification} entity.
 */
public class EntityLastModificationDTO implements Serializable {
    
    private Long id;

    private ZonedDateTime lastModified;


    private Long lastModifiedById;

    private String lastModifiedByLogin;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Long getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(Long userId) {
        this.lastModifiedById = userId;
    }

    public String getLastModifiedByLogin() {
        return lastModifiedByLogin;
    }

    public void setLastModifiedByLogin(String userLogin) {
        this.lastModifiedByLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityLastModificationDTO)) {
            return false;
        }

        return id != null && id.equals(((EntityLastModificationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityLastModificationDTO{" +
            "id=" + getId() +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedById=" + getLastModifiedById() +
            ", lastModifiedByLogin='" + getLastModifiedByLogin() + "'" +
            "}";
    }
}

package com.astrid.diaspora.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.astrid.diaspora.domain.ProjectStatus} entity.
 */
public class ProjectStatusDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer daysToNotification;

    
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

    public Integer getDaysToNotification() {
        return daysToNotification;
    }

    public void setDaysToNotification(Integer daysToNotification) {
        this.daysToNotification = daysToNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectStatusDTO)) {
            return false;
        }

        return id != null && id.equals(((ProjectStatusDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectStatusDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", daysToNotification=" + getDaysToNotification() +
            "}";
    }
}

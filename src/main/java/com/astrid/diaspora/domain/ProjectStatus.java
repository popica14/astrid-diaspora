package com.astrid.diaspora.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProjectStatus.
 */
@Entity
@Table(name = "project_status")
public class ProjectStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "days_to_notification", nullable = false)
    private Integer daysToNotification;

    @NotNull
    @Column(name = "jhi_order", nullable = false)
    private Integer order;

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

    public ProjectStatus name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDaysToNotification() {
        return daysToNotification;
    }

    public ProjectStatus daysToNotification(Integer daysToNotification) {
        this.daysToNotification = daysToNotification;
        return this;
    }

    public void setDaysToNotification(Integer daysToNotification) {
        this.daysToNotification = daysToNotification;
    }

    public Integer getOrder() {
        return order;
    }

    public ProjectStatus order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectStatus)) {
            return false;
        }
        return id != null && id.equals(((ProjectStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectStatus{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", daysToNotification=" + getDaysToNotification() +
            ", order=" + getOrder() +
            "}";
    }
}

package com.astrid.diaspora.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.astrid.diaspora.domain.Location} entity.
 */
public class LocationDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String county;

    private String address;

    
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocationDTO)) {
            return false;
        }

        return id != null && id.equals(((LocationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LocationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", county='" + getCounty() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}

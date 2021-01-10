package com.astrid.diaspora.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.astrid.diaspora.domain.enumeration.BeneficiaryType;

/**
 * A DTO for the {@link com.astrid.diaspora.domain.Beneficiary} entity.
 */
public class BeneficiaryDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private BeneficiaryType type;

    private String address;

    private String contact;


    private Long contactPersonId;

    private String contactPersonLogin;
    
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

    public BeneficiaryType getType() {
        return type;
    }

    public void setType(BeneficiaryType type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Long getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(Long userId) {
        this.contactPersonId = userId;
    }

    public String getContactPersonLogin() {
        return contactPersonLogin;
    }

    public void setContactPersonLogin(String userLogin) {
        this.contactPersonLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BeneficiaryDTO)) {
            return false;
        }

        return id != null && id.equals(((BeneficiaryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BeneficiaryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", address='" + getAddress() + "'" +
            ", contact='" + getContact() + "'" +
            ", contactPersonId=" + getContactPersonId() +
            ", contactPersonLogin='" + getContactPersonLogin() + "'" +
            "}";
    }
}

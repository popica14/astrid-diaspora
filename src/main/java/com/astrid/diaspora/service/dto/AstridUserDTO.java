package com.astrid.diaspora.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.astrid.diaspora.domain.enumeration.Gender;
import com.astrid.diaspora.domain.enumeration.Education;

/**
 * A DTO for the {@link com.astrid.diaspora.domain.AstridUser} entity.
 */
public class AstridUserDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String residency;

    @NotNull
    private Gender gender;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Education highestEducation;


    private Long userId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResidency() {
        return residency;
    }

    public void setResidency(String residency) {
        this.residency = residency;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Education getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(Education highestEducation) {
        this.highestEducation = highestEducation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AstridUserDTO)) {
            return false;
        }

        return id != null && id.equals(((AstridUserDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AstridUserDTO{" +
            "id=" + getId() +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", residency='" + getResidency() + "'" +
            ", gender='" + getGender() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", highestEducation='" + getHighestEducation() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}

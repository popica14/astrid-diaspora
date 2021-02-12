package com.astrid.diaspora.web.rest.vm;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.astrid.diaspora.domain.enumeration.Education;
import com.astrid.diaspora.domain.enumeration.Gender;
import com.astrid.diaspora.service.dto.UserDTO;
import javax.validation.constraints.Size;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    private String phoneNumber;
    private String residency;
    private Gender gender;
    private LocalDate birthDate;
    private Education highestEducation;

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

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phone){
        this.phoneNumber = phone;
    }
    // prettier-ignore
    @Override
    public String toString() {
        return "ManagedUserVM{" + super.toString() + "} ";
    }
}

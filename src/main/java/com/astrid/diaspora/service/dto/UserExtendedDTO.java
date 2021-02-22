package com.astrid.diaspora.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.astrid.diaspora.config.Constants;
import com.astrid.diaspora.domain.Authority;
import com.astrid.diaspora.domain.User;
import com.astrid.diaspora.domain.enumeration.Education;
import com.astrid.diaspora.domain.enumeration.Gender;
import com.astrid.diaspora.web.rest.vm.ManagedUserVM;

/**
 * A DTO representing an extended user, with his authorities.
 */
public class UserExtendedDTO extends UserDTO {

    private Long userId;

    private Long astridUserId;

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

    public UserExtendedDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserExtendedDTO(User user) {
        super(user);
    }

    public UserExtendedDTO(Long id, String login, String firstName, String lastName, String email, String imageUrl,
                           String langKey, String createdBy, Instant createdDate, String lastModifiedBy,
                           Instant lastModifiedDate, Set<String> authorities, LocalDate birthDate,
                           Gender gender, Education highestEducation, String phoneNumber, String residency) {
        super(id, login, firstName, lastName, email, imageUrl, langKey, createdBy, createdDate, lastModifiedBy, lastModifiedDate,
            authorities);
        this.gender = gender;
        this.highestEducation = highestEducation;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.residency = residency;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAstridUserId() {
        return astridUserId;
    }

    public void setAstridUserId(Long id) {
        this.astridUserId = id;
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
}

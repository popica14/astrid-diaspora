package com.astrid.diaspora.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.astrid.diaspora.domain.enumeration.Gender;

import com.astrid.diaspora.domain.enumeration.Education;

/**
 * A AstridUser.
 */
@Entity
@Table(name = "astrid_user")
public class AstridUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "residency", nullable = false)
    private String residency;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "highest_education", nullable = false)
    private Education highestEducation;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AstridUser phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResidency() {
        return residency;
    }

    public AstridUser residency(String residency) {
        this.residency = residency;
        return this;
    }

    public void setResidency(String residency) {
        this.residency = residency;
    }

    public Gender getGender() {
        return gender;
    }

    public AstridUser gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public AstridUser birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Education getHighestEducation() {
        return highestEducation;
    }

    public AstridUser highestEducation(Education highestEducation) {
        this.highestEducation = highestEducation;
        return this;
    }

    public void setHighestEducation(Education highestEducation) {
        this.highestEducation = highestEducation;
    }

    public User getUser() {
        return user;
    }

    public AstridUser user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AstridUser)) {
            return false;
        }
        return id != null && id.equals(((AstridUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AstridUser{" +
            "id=" + getId() +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", residency='" + getResidency() + "'" +
            ", gender='" + getGender() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", highestEducation='" + getHighestEducation() + "'" +
            "}";
    }
}

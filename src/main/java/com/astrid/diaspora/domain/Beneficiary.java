package com.astrid.diaspora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.astrid.diaspora.domain.enumeration.BeneficiaryType;

/**
 * A Beneficiary.
 */
@Entity
@Table(name = "beneficiary")
public class Beneficiary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private BeneficiaryType type;

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contact_person")
    private String contactPerson;

    @ManyToMany(mappedBy = "beneficiaries")
    @JsonIgnore
    private Set<AstridProject> projects = new HashSet<>();

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

    public Beneficiary name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeneficiaryType getType() {
        return type;
    }

    public Beneficiary type(BeneficiaryType type) {
        this.type = type;
        return this;
    }

    public void setType(BeneficiaryType type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public Beneficiary address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Beneficiary phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Beneficiary email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Beneficiary contactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Set<AstridProject> getProjects() {
        return projects;
    }

    public Beneficiary projects(Set<AstridProject> astridProjects) {
        this.projects = astridProjects;
        return this;
    }

    public Beneficiary addProjects(AstridProject astridProject) {
        this.projects.add(astridProject);
        astridProject.getBeneficiaries().add(this);
        return this;
    }

    public Beneficiary removeProjects(AstridProject astridProject) {
        this.projects.remove(astridProject);
        astridProject.getBeneficiaries().remove(this);
        return this;
    }

    public void setProjects(Set<AstridProject> astridProjects) {
        this.projects = astridProjects;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Beneficiary)) {
            return false;
        }
        return id != null && id.equals(((Beneficiary) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Beneficiary{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            "}";
    }
}

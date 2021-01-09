package com.astrid.diaspora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

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

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private BeneficiaryType type;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @ManyToOne
    @JsonIgnoreProperties(value = "beneficiaries", allowSetters = true)
    private User contactPerson;

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

    public String getContact() {
        return contact;
    }

    public Beneficiary contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public User getContactPerson() {
        return contactPerson;
    }

    public Beneficiary contactPerson(User user) {
        this.contactPerson = user;
        return this;
    }

    public void setContactPerson(User user) {
        this.contactPerson = user;
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
            ", contact='" + getContact() + "'" +
            "}";
    }
}

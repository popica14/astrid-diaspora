package com.astrid.diaspora.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A AstridProject.
 */
@Entity
@Table(name = "astrid_project")
public class AstridProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Lob
    @Column(name = "documentation")
    private byte[] documentation;

    @Column(name = "documentation_content_type")
    private String documentationContentType;

    @Column(name = "needed_amount")
    private String neededAmount;

    @Column(name = "current_amount")
    private String currentAmount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "supporters")
    private Integer supporters;

    @Column(name = "goal")
    private String goal;

    @Column(name = "status_reason")
    private String statusReason;

    @Column(name = "status_deadline")
    private ZonedDateTime statusDeadline;

    @OneToOne
    @JoinColumn(unique = true)
    private EntityCreation entityCreation;

    @OneToOne
    @JoinColumn(unique = true)
    private EntityLastModification entityLastModification;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "astridProjects", allowSetters = true)
    private User responsible;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "astridProjects", allowSetters = true)
    private User initiator;

    @ManyToOne
    @JsonIgnoreProperties(value = "astridProjects", allowSetters = true)
    private ProjectStatus status;

    @ManyToOne
    @JsonIgnoreProperties(value = "astridProjects", allowSetters = true)
    private Location location;

    @ManyToMany
    @JoinTable(name = "astrid_project_implementation_team",
               joinColumns = @JoinColumn(name = "astrid_project_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "implementation_team_id", referencedColumnName = "id"))
    private Set<User> implementationTeams = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "astrid_project_beneficiaries",
               joinColumns = @JoinColumn(name = "astrid_project_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "beneficiaries_id", referencedColumnName = "id"))
    private Set<Beneficiary> beneficiaries = new HashSet<>();

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

    public AstridProject name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public AstridProject shortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public byte[] getDocumentation() {
        return documentation;
    }

    public AstridProject documentation(byte[] documentation) {
        this.documentation = documentation;
        return this;
    }

    public void setDocumentation(byte[] documentation) {
        this.documentation = documentation;
    }

    public String getDocumentationContentType() {
        return documentationContentType;
    }

    public AstridProject documentationContentType(String documentationContentType) {
        this.documentationContentType = documentationContentType;
        return this;
    }

    public void setDocumentationContentType(String documentationContentType) {
        this.documentationContentType = documentationContentType;
    }

    public String getNeededAmount() {
        return neededAmount;
    }

    public AstridProject neededAmount(String neededAmount) {
        this.neededAmount = neededAmount;
        return this;
    }

    public void setNeededAmount(String neededAmount) {
        this.neededAmount = neededAmount;
    }

    public String getCurrentAmount() {
        return currentAmount;
    }

    public AstridProject currentAmount(String currentAmount) {
        this.currentAmount = currentAmount;
        return this;
    }

    public void setCurrentAmount(String currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public AstridProject currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getSupporters() {
        return supporters;
    }

    public AstridProject supporters(Integer supporters) {
        this.supporters = supporters;
        return this;
    }

    public void setSupporters(Integer supporters) {
        this.supporters = supporters;
    }

    public String getGoal() {
        return goal;
    }

    public AstridProject goal(String goal) {
        this.goal = goal;
        return this;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public AstridProject statusReason(String statusReason) {
        this.statusReason = statusReason;
        return this;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public ZonedDateTime getStatusDeadline() {
        return statusDeadline;
    }

    public AstridProject statusDeadline(ZonedDateTime statusDeadline) {
        this.statusDeadline = statusDeadline;
        return this;
    }

    public void setStatusDeadline(ZonedDateTime statusDeadline) {
        this.statusDeadline = statusDeadline;
    }

    public EntityCreation getEntityCreation() {
        return entityCreation;
    }

    public AstridProject entityCreation(EntityCreation entityCreation) {
        this.entityCreation = entityCreation;
        return this;
    }

    public void setEntityCreation(EntityCreation entityCreation) {
        this.entityCreation = entityCreation;
    }

    public EntityLastModification getEntityLastModification() {
        return entityLastModification;
    }

    public AstridProject entityLastModification(EntityLastModification entityLastModification) {
        this.entityLastModification = entityLastModification;
        return this;
    }

    public void setEntityLastModification(EntityLastModification entityLastModification) {
        this.entityLastModification = entityLastModification;
    }

    public User getResponsible() {
        return responsible;
    }

    public AstridProject responsible(User user) {
        this.responsible = user;
        return this;
    }

    public void setResponsible(User user) {
        this.responsible = user;
    }

    public User getInitiator() {
        return initiator;
    }

    public AstridProject initiator(User user) {
        this.initiator = user;
        return this;
    }

    public void setInitiator(User user) {
        this.initiator = user;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public AstridProject status(ProjectStatus projectStatus) {
        this.status = projectStatus;
        return this;
    }

    public void setStatus(ProjectStatus projectStatus) {
        this.status = projectStatus;
    }

    public Location getLocation() {
        return location;
    }

    public AstridProject location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<User> getImplementationTeams() {
        return implementationTeams;
    }

    public AstridProject implementationTeams(Set<User> users) {
        this.implementationTeams = users;
        return this;
    }

    public AstridProject addImplementationTeam(User user) {
        this.implementationTeams.add(user);
        return this;
    }

    public AstridProject removeImplementationTeam(User user) {
        this.implementationTeams.remove(user);
        return this;
    }

    public void setImplementationTeams(Set<User> users) {
        this.implementationTeams = users;
    }

    public Set<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public AstridProject beneficiaries(Set<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
        return this;
    }

    public AstridProject addBeneficiaries(Beneficiary beneficiary) {
        this.beneficiaries.add(beneficiary);
        beneficiary.getProjects().add(this);
        return this;
    }

    public AstridProject removeBeneficiaries(Beneficiary beneficiary) {
        this.beneficiaries.remove(beneficiary);
        beneficiary.getProjects().remove(this);
        return this;
    }

    public void setBeneficiaries(Set<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AstridProject)) {
            return false;
        }
        return id != null && id.equals(((AstridProject) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AstridProject{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortDescription='" + getShortDescription() + "'" +
            ", documentation='" + getDocumentation() + "'" +
            ", documentationContentType='" + getDocumentationContentType() + "'" +
            ", neededAmount='" + getNeededAmount() + "'" +
            ", currentAmount='" + getCurrentAmount() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", supporters=" + getSupporters() +
            ", goal='" + getGoal() + "'" +
            ", statusReason='" + getStatusReason() + "'" +
            ", statusDeadline='" + getStatusDeadline() + "'" +
            "}";
    }
}

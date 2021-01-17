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

    @Column(name = "needed_amount")
    private String neededAmount;

    @Column(name = "current_amount")
    private String currentAmount;

    @Column(name = "supporters")
    private Integer supporters;

    @NotNull
    @Column(name = "goal", nullable = false)
    private String goal;

    @NotNull
    @Column(name = "status_reason", nullable = false)
    private String statusReason;

    @Column(name = "status_deadline")
    private ZonedDateTime statusDeadline;

    @Lob
    @Column(name = "documentation_1")
    private byte[] documentation1;

    @Column(name = "documentation_1_content_type")
    private String documentation1ContentType;

    @Lob
    @Column(name = "documentation_2")
    private byte[] documentation2;

    @Column(name = "documentation_2_content_type")
    private String documentation2ContentType;

    @Lob
    @Column(name = "documentation_3")
    private byte[] documentation3;

    @Column(name = "documentation_3_content_type")
    private String documentation3ContentType;

    @Lob
    @Column(name = "documentation_4")
    private byte[] documentation4;

    @Column(name = "documentation_4_content_type")
    private String documentation4ContentType;

    @Lob
    @Column(name = "documentation_5")
    private byte[] documentation5;

    @Column(name = "documentation_5_content_type")
    private String documentation5ContentType;

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

    @ManyToOne
    @JsonIgnoreProperties(value = "astridProjects", allowSetters = true)
    private User initiator;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "astridProjects", allowSetters = true)
    private ProjectStatus status;

    @ManyToOne
    @JsonIgnoreProperties(value = "astridProjects", allowSetters = true)
    private Location location;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "astridProjects", allowSetters = true)
    private Currency currency;

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

    public byte[] getDocumentation1() {
        return documentation1;
    }

    public AstridProject documentation1(byte[] documentation1) {
        this.documentation1 = documentation1;
        return this;
    }

    public void setDocumentation1(byte[] documentation1) {
        this.documentation1 = documentation1;
    }

    public String getDocumentation1ContentType() {
        return documentation1ContentType;
    }

    public AstridProject documentation1ContentType(String documentation1ContentType) {
        this.documentation1ContentType = documentation1ContentType;
        return this;
    }

    public void setDocumentation1ContentType(String documentation1ContentType) {
        this.documentation1ContentType = documentation1ContentType;
    }

    public byte[] getDocumentation2() {
        return documentation2;
    }

    public AstridProject documentation2(byte[] documentation2) {
        this.documentation2 = documentation2;
        return this;
    }

    public void setDocumentation2(byte[] documentation2) {
        this.documentation2 = documentation2;
    }

    public String getDocumentation2ContentType() {
        return documentation2ContentType;
    }

    public AstridProject documentation2ContentType(String documentation2ContentType) {
        this.documentation2ContentType = documentation2ContentType;
        return this;
    }

    public void setDocumentation2ContentType(String documentation2ContentType) {
        this.documentation2ContentType = documentation2ContentType;
    }

    public byte[] getDocumentation3() {
        return documentation3;
    }

    public AstridProject documentation3(byte[] documentation3) {
        this.documentation3 = documentation3;
        return this;
    }

    public void setDocumentation3(byte[] documentation3) {
        this.documentation3 = documentation3;
    }

    public String getDocumentation3ContentType() {
        return documentation3ContentType;
    }

    public AstridProject documentation3ContentType(String documentation3ContentType) {
        this.documentation3ContentType = documentation3ContentType;
        return this;
    }

    public void setDocumentation3ContentType(String documentation3ContentType) {
        this.documentation3ContentType = documentation3ContentType;
    }

    public byte[] getDocumentation4() {
        return documentation4;
    }

    public AstridProject documentation4(byte[] documentation4) {
        this.documentation4 = documentation4;
        return this;
    }

    public void setDocumentation4(byte[] documentation4) {
        this.documentation4 = documentation4;
    }

    public String getDocumentation4ContentType() {
        return documentation4ContentType;
    }

    public AstridProject documentation4ContentType(String documentation4ContentType) {
        this.documentation4ContentType = documentation4ContentType;
        return this;
    }

    public void setDocumentation4ContentType(String documentation4ContentType) {
        this.documentation4ContentType = documentation4ContentType;
    }

    public byte[] getDocumentation5() {
        return documentation5;
    }

    public AstridProject documentation5(byte[] documentation5) {
        this.documentation5 = documentation5;
        return this;
    }

    public void setDocumentation5(byte[] documentation5) {
        this.documentation5 = documentation5;
    }

    public String getDocumentation5ContentType() {
        return documentation5ContentType;
    }

    public AstridProject documentation5ContentType(String documentation5ContentType) {
        this.documentation5ContentType = documentation5ContentType;
        return this;
    }

    public void setDocumentation5ContentType(String documentation5ContentType) {
        this.documentation5ContentType = documentation5ContentType;
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

    public Currency getCurrency() {
        return currency;
    }

    public AstridProject currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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
            ", neededAmount='" + getNeededAmount() + "'" +
            ", currentAmount='" + getCurrentAmount() + "'" +
            ", supporters=" + getSupporters() +
            ", goal='" + getGoal() + "'" +
            ", statusReason='" + getStatusReason() + "'" +
            ", statusDeadline='" + getStatusDeadline() + "'" +
            ", documentation1='" + getDocumentation1() + "'" +
            ", documentation1ContentType='" + getDocumentation1ContentType() + "'" +
            ", documentation2='" + getDocumentation2() + "'" +
            ", documentation2ContentType='" + getDocumentation2ContentType() + "'" +
            ", documentation3='" + getDocumentation3() + "'" +
            ", documentation3ContentType='" + getDocumentation3ContentType() + "'" +
            ", documentation4='" + getDocumentation4() + "'" +
            ", documentation4ContentType='" + getDocumentation4ContentType() + "'" +
            ", documentation5='" + getDocumentation5() + "'" +
            ", documentation5ContentType='" + getDocumentation5ContentType() + "'" +
            "}";
    }
}

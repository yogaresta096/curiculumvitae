package com.handayanto.curiculumvitae.model;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotNull(message = "First Name Cannot be Null")
    @Size(min = 1, max = 10)
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 20)
    private String lastName;

    @Column(name = "born_date", nullable = false)
    private LocalDate bornDate;

    @Column
    @Size(max = 50)
    private String address;

    @ElementCollection
    @CollectionTable(name = "education", joinColumns = @JoinColumn(name = "personal_id"))
    private List<EducationInfo> educations;

    @Embeddable
    @Data
    public static class EducationInfo {
        private String degree;
        private String institution;
        private String year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<EducationInfo> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationInfo> educations) {
        this.educations = educations;
    }
}

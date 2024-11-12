package com.handayanto.curiculumvitae.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private String bornDate;

    @Column
    @Size(max = 50)
    private String address;

    @ElementCollection
    @CollectionTable(name = "education", joinColumns = @JoinColumn(name = "personal_id"))
    private List<EducationInfo> educations;

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Experiences> experiences;

    @Embeddable
    @Data
    public static class EducationInfo {
        private String degree;
        private String institution;
        private String year;
    }
}

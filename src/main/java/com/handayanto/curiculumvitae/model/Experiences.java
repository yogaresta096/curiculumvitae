package com.handayanto.curiculumvitae.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Experiences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExperience;

    @Column(name = "personal_id", nullable = false)
    private Long personalId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;


}

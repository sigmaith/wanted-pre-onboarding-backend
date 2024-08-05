package com.example.preonboarding.company.domain;

import com.example.preonboarding.job_posting.domain.JobPosting;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    private String companyName;
    private String country;
    private String region;

    @OneToMany(mappedBy = "company")
    private List<JobPosting> jobPostings;

}

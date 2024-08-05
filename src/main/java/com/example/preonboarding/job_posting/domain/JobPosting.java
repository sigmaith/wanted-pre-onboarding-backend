package com.example.preonboarding.job_posting.domain;

import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.job_application.domain.JobApplication;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_posting_id")
    private Long id;

    private String position;
    private int compensation;
    private String content;
    private String techStack;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "jobPosting")
    private List<JobApplication> jobApplications;
}

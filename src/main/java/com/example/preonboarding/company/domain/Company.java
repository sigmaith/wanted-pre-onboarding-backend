package com.example.preonboarding.company.domain;

import com.example.preonboarding.job_posting.domain.JobPosting;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    private String name;
    private String country;
    private String region;

    @OneToMany(mappedBy = "company")
    private List<JobPosting> jobPostings;

    public void setJobPostings(List<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }
}

package com.example.preonboarding.job_posting.domain;

import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.job_application.domain.JobApplication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder
    public JobPosting(String position, int compensation, String content, String techStack, Company company) {
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.techStack = techStack;
        this.company = company;
    }

    public void update(String position, int compensation, String content, String techStack) {
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.techStack = techStack;
    }
}

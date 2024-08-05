package com.example.preonboarding.job_application.domain;

import com.example.preonboarding.job_posting.domain.JobPosting;
import com.example.preonboarding.user.domain.Member;
import jakarta.persistence.*;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_application_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    private String status;
}

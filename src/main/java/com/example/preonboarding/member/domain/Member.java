package com.example.preonboarding.member.domain;

import com.example.preonboarding.job_application.domain.JobApplication;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<JobApplication> jobApplications;
}

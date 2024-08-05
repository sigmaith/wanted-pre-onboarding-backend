package com.example.preonboarding.job_application.repository;


import com.example.preonboarding.job_application.domain.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    Optional<JobApplication> findByJobPostingAndMember(Long jobPostingId, Long memberId);
}

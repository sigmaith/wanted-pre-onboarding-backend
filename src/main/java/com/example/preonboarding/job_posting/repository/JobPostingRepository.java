package com.example.preonboarding.job_posting.repository;

import com.example.preonboarding.job_posting.domain.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long>, JobPostingRepositoryCustom {
}

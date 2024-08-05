package com.example.preonboarding.job_posting.repository;

import com.example.preonboarding.job_posting.domain.JobPosting;
import java.util.List;

public interface JobPostingRepositoryCustom {
    List<JobPosting> searchJobPostings(String search);
}

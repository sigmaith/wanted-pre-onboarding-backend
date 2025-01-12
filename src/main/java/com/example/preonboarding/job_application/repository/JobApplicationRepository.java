package com.example.preonboarding.job_application.repository;


import com.example.preonboarding.job_application.domain.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
//    Optional<JobApplication> findByJobPostingAndMember(Long jobPostingId, Long memberId);
    @Query("SELECT ja FROM JobApplication ja WHERE ja.jobPosting.id = :jobPostingId AND ja.member.id = :memberId")
    Optional<JobApplication> findByJobPostingAndMember(@Param("jobPostingId") Long jobPostingId, @Param("memberId") Long memberId);

}

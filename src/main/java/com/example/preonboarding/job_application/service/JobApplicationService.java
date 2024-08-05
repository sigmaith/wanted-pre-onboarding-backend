package com.example.preonboarding.job_application.service;

import com.example.preonboarding.common.exception.JobPostingNotFoundException;
import com.example.preonboarding.common.exception.MemberNotFoundException;
import com.example.preonboarding.job_application.domain.JobApplication;
import com.example.preonboarding.job_application.domain.JobApplicationStatus;
import com.example.preonboarding.job_application.dto.JobApplicationRequestDto;
import com.example.preonboarding.job_application.repository.JobApplicationRepository;
import com.example.preonboarding.job_posting.domain.JobPosting;
import com.example.preonboarding.job_posting.repository.JobPostingRepository;
import com.example.preonboarding.member.domain.Member;
import com.example.preonboarding.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationService.class);

    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostingRepository jobPostingRepository;
    private final MemberRepository memberRepository;

    public void applyToJob(JobApplicationRequestDto jobApplicationRequestDto) {
        Long jobPostingId = jobApplicationRequestDto.getJobPostingId();
        Long memberId = jobApplicationRequestDto.getMemberId();

        JobPosting jobPosting = getJobPosting(jobPostingId);
        Member member = getMember(memberId);

        logger.debug("Checking existing job application for member ID: {} and job posting ID: {}", memberId, jobPostingId);
        JobApplication jobApplication = jobApplicationRepository.findByJobPostingAndMember(jobPosting.getId(), member.getId())
                .orElse(null);

        if (jobApplication != null) {
            logger.debug("Found existing job application. Applying to job posting.");
            jobApplication.apply();
        } else {
            logger.debug("No existing job application found. Creating new job application.");
            jobApplication = createNewJobApplication(jobPosting, member);
        }

        logger.debug("Saving job application to repository.");
        jobApplicationRepository.save(jobApplication);
        logger.info("Member {} applied to job posting {}", memberId, jobPostingId);
    }

    private JobPosting getJobPosting(Long jobPostingId) {
        logger.debug("Finding job posting with ID: {}", jobPostingId);
        return jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new JobPostingNotFoundException("Job posting not found with ID: " + jobPostingId));
    }

    private Member getMember(Long memberId) {
        logger.debug("Finding member with ID: {}", memberId);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + memberId));
    }

    private JobApplication createNewJobApplication(JobPosting jobPosting, Member member) {
        logger.debug("Creating new job application for member ID: {} and job posting ID: {}", member.getId(), jobPosting.getId());
        return JobApplication.builder()
                .jobPosting(jobPosting)
                .member(member)
                .status(JobApplicationStatus.지원완료) // 초기 상태를 지원완료로 설정
                .build();
    }
}

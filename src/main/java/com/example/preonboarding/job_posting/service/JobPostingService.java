package com.example.preonboarding.job_posting.service;

import com.example.preonboarding.common.exception.CompanyNotFoundException;
import com.example.preonboarding.common.exception.JobPostingNotFoundException;
import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.company.repository.CompanyRepository;
import com.example.preonboarding.job_posting.domain.JobPosting;
import com.example.preonboarding.job_posting.dto.JobPostingUpdateDto;
import com.example.preonboarding.job_posting.repository.JobPostingRepository;
import com.example.preonboarding.job_posting.dto.JobPostingCreateDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobPostingService {

    private static final Logger logger = LoggerFactory.getLogger(JobPostingService.class);

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    public void createJobPosting(JobPostingCreateDto jobPostingCreateDto) {
        Company company = findCompanyById(jobPostingCreateDto.getCompanyId());
        JobPosting jobPosting = JobPostingCreateDto.toEntity(jobPostingCreateDto, company);
        jobPostingRepository.save(jobPosting);
        logger.info("Job posting created successfully for company ID {}", jobPostingCreateDto.getCompanyId());
    }

    public void updateJobPosting(Long id, JobPostingUpdateDto updateDto) {
        logger.debug("Updating job posting with ID: {} and data: {}", id, updateDto);
        JobPosting jobPosting = findJobPostingById(id);

        jobPosting.update(updateDto.getPosition(),
                updateDto.getCompensation(),
                updateDto.getContent(),
                updateDto.getTechStack());

        jobPostingRepository.save(jobPosting);
        logger.info("Job posting with ID {} updated successfully", id);
    }

    private Company findCompanyById(Long companyId) {
        try {
            logger.debug("Finding company with ID: {}", companyId);
            return companyRepository.findById(companyId)
                    .orElseThrow(() -> new CompanyNotFoundException("Company with ID " + companyId + " not found"));
        } catch (CompanyNotFoundException e) {
            logger.error("Company with ID {} not found", companyId, e);
            throw e;
        }
    }

    private JobPosting findJobPostingById(Long jobPostingId) {
        try {
            logger.debug("Finding job posting with ID: {}", jobPostingId);
            return jobPostingRepository.findById(jobPostingId)
                    .orElseThrow(() -> new JobPostingNotFoundException("JobPosting with ID " + jobPostingId + " not found"));
        } catch (JobPostingNotFoundException e) {
            logger.error("Job posting with ID {} not found", jobPostingId, e);
            throw e;
        }
    }
}

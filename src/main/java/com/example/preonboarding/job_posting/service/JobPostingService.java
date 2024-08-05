package com.example.preonboarding.job_posting.service;

import com.example.preonboarding.common.exception.CompanyNotFoundException;
import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.company.repository.CompanyRepository;
import com.example.preonboarding.job_posting.domain.JobPosting;
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

    private Company findCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with ID " + companyId + " not found"));
    }
}

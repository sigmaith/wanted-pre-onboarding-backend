package com.example.preonboarding.job_posting.service;

import com.example.preonboarding.common.exception.CompanyNotFoundException;
import com.example.preonboarding.common.exception.JobPostingNotFoundException;
import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.company.repository.CompanyRepository;
import com.example.preonboarding.job_posting.domain.JobPosting;
import com.example.preonboarding.job_posting.dto.JobPostingDetailsResponseDto;
import com.example.preonboarding.job_posting.dto.JobPostingResponseDto;
import com.example.preonboarding.job_posting.dto.JobPostingUpdateDto;
import com.example.preonboarding.job_posting.repository.JobPostingRepository;
import com.example.preonboarding.job_posting.dto.JobPostingCreateDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteJobPosting(Long id) {
        logger.debug("Deleting job posting with ID: {}", id);
        JobPosting jobPosting = findJobPostingById(id);
        jobPostingRepository.delete(jobPosting);
        logger.info("Job posting with ID {} deleted successfully", id);
    }

    public List<JobPostingResponseDto> getAllJobPostings() {
        logger.debug("Getting all job postings");
        List<JobPosting> jobPostings = jobPostingRepository.findAll();
        return jobPostings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<JobPostingResponseDto> searchJobPostings(String search) {
        logger.debug("Searching job postings with search term: {}", search);
        List<JobPosting> jobPostings = jobPostingRepository.searchJobPostings(search);
        return jobPostings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public JobPostingDetailsResponseDto getJobPostingDetails(Long id) {
        logger.debug("Getting job posting details with ID: {}", id);
        JobPosting jobPosting = findJobPostingById(id);
        Company company = jobPosting.getCompany();

        List<Long> otherJobPostings = company.getJobPostings().stream()
                .map(JobPosting::getId)
                .filter(postingId -> !postingId.equals(id))
                .collect(Collectors.toList());

        return convertToDetailDto(otherJobPostings, jobPosting, company);
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

    private JobPostingResponseDto convertToDto(JobPosting jobPosting) {
        return new JobPostingResponseDto(
                jobPosting.getId(),
                jobPosting.getCompany().getName(),
                jobPosting.getCompany().getCountry(),
                jobPosting.getCompany().getRegion(),
                jobPosting.getPosition(),
                jobPosting.getCompensation(),
                jobPosting.getTechStack()
        );
    }
    private JobPostingDetailsResponseDto convertToDetailDto(List<Long> otherJobPostings, JobPosting jobPosting, Company company) {
        return new JobPostingDetailsResponseDto(
                jobPosting.getId(),
                company.getName(),
                company.getCountry(),
                company.getRegion(),
                jobPosting.getPosition(),
                jobPosting.getCompensation(),
                jobPosting.getTechStack(),
                jobPosting.getContent(),
                otherJobPostings
        );
    }
}

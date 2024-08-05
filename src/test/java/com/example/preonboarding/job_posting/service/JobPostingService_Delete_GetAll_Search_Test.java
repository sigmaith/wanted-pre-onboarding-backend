package com.example.preonboarding.job_posting.service;

import com.example.preonboarding.common.exception.JobPostingNotFoundException;
import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.company.repository.CompanyRepository;
import com.example.preonboarding.job_posting.domain.JobPosting;
import com.example.preonboarding.job_posting.dto.JobPostingResponseDto;
import com.example.preonboarding.job_posting.repository.JobPostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class JobPostingService_Delete_GetAll_Search_Test {

    @Mock
    private JobPostingRepository jobPostingRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private JobPostingService jobPostingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteJobPosting_notFound() {
        when(jobPostingRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(JobPostingNotFoundException.class, () -> jobPostingService.deleteJobPosting(1L));
    }

    @Test
    void getAllJobPostings_success() {
        // Given
        Company company = new Company();
        List<JobPosting> jobPostings = new ArrayList<>();
        JobPosting jobPosting = JobPosting.builder()
                .company(company)
                .build();

        jobPostings.add(jobPosting);

        when(jobPostingRepository.findAll()).thenReturn(jobPostings);

        // When
        List<JobPostingResponseDto> result = jobPostingService.getAllJobPostings();

        // Then
        assertFalse(result.isEmpty());
        verify(jobPostingRepository).findAll();
    }

    @Test
    void searchJobPostings_success() {
        // Given
        Company company = new Company();
        List<JobPosting> jobPostings = new ArrayList<>();
        JobPosting jobPosting = JobPosting.builder()
                .company(company)
                .build();

        jobPostings.add(jobPosting);

        when(jobPostingRepository.searchJobPostings(any())).thenReturn(jobPostings);

        // When
        List<JobPostingResponseDto> result = jobPostingService.searchJobPostings("Developer");

        // Then
        assertFalse(result.isEmpty());
        verify(jobPostingRepository).searchJobPostings("Developer");
    }
}
package com.example.preonboarding.job_posting.service;

import com.example.preonboarding.common.exception.CompanyNotFoundException;
import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.company.repository.CompanyRepository;
import com.example.preonboarding.job_posting.domain.JobPosting;
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
import com.example.preonboarding.job_posting.dto.JobPostingCreateDto;

class JobPostingServiceTest {

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
    void createJobPosting_success() {
        // Given
        Company company = new Company();
        when(companyRepository.findById(anyLong())).thenReturn(java.util.Optional.of(company));

        JobPostingCreateDto createDto = JobPostingCreateDto.builder()
                .companyId(1L)
                .position("Developer")
                .compensation(100000)
                .content("Job description")
                .techStack("Java")
                .build();

        // When
        jobPostingService.createJobPosting(createDto);

        // Then
        verify(jobPostingRepository).save(any(JobPosting.class));
    }

    @Test
    void createJobPosting_companyNotFound() {
        // Given
        when(companyRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        JobPostingCreateDto createDto = JobPostingCreateDto.builder()
                .companyId(1L)
                .position("Developer")
                .compensation(100000)
                .content("Job description")
                .techStack("Java")
                .build();

        // When & Then
        assertThrows(CompanyNotFoundException.class, () -> jobPostingService.createJobPosting(createDto));
    }
}
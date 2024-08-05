package com.example.preonboarding.job_posting.service;

import com.example.preonboarding.common.exception.CompanyNotFoundException;
import com.example.preonboarding.common.exception.JobPostingNotFoundException;
import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.company.repository.CompanyRepository;
import com.example.preonboarding.job_posting.domain.JobPosting;
import com.example.preonboarding.job_posting.dto.JobPostingResponseDto;
import com.example.preonboarding.job_posting.dto.JobPostingUpdateDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void updateJobPosting_success() {
        // Given
        JobPosting jobPosting = new JobPosting();
        when(jobPostingRepository.findById(anyLong())).thenReturn(java.util.Optional.of(jobPosting));

        JobPostingUpdateDto updateDto = JobPostingUpdateDto.builder()
                .position("Senior Developer")
                .compensation(120000)
                .content("Updated job description")
                .techStack("Kotlin")
                .build();

        // When
        jobPostingService.updateJobPosting(1L, updateDto);

        // Then
        verify(jobPostingRepository).save(any(JobPosting.class));
    }

    @Test
    void updateJobPosting_notFound() {
        // Given
        when(jobPostingRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        JobPostingUpdateDto updateDto = JobPostingUpdateDto.builder()
                .position("Senior Developer")
                .compensation(120000)
                .content("Updated job description")
                .techStack("Kotlin")
                .build();

        // When & Then
        assertThrows(JobPostingNotFoundException.class, () -> jobPostingService.updateJobPosting(1L, updateDto));
    }

    @Test
    void deleteJobPosting_success() {
        JobPosting jobPosting = new JobPosting();
        when(jobPostingRepository.findById(anyLong())).thenReturn(Optional.of(jobPosting));

        jobPostingService.deleteJobPosting(1L);

        verify(jobPostingRepository).delete(any(JobPosting.class));
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
package com.example.preonboarding.job_posting.service;

import com.example.preonboarding.common.exception.JobPostingNotFoundException;
import com.example.preonboarding.company.repository.CompanyRepository;
import com.example.preonboarding.job_posting.domain.JobPosting;
import com.example.preonboarding.job_posting.dto.JobPostingUpdateDto;
import com.example.preonboarding.job_posting.repository.JobPostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JobPostingService_UpdateTest {
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
}

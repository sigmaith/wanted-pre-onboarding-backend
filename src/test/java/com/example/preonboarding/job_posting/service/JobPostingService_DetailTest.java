package com.example.preonboarding.job_posting.service;

import com.example.preonboarding.common.exception.JobPostingNotFoundException;
import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.job_posting.domain.JobPosting;
import com.example.preonboarding.job_posting.dto.JobPostingDetailsResponseDto;
import com.example.preonboarding.job_posting.repository.JobPostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class JobPostingService_DetailTest {

    @Mock
    private JobPostingRepository jobPostingRepository;

    @InjectMocks
    private JobPostingService jobPostingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getJobPostingDetails_success() {
        // Given
        Company company = new Company();
        JobPosting jobPosting = JobPosting.builder()
                .id(1L)
                .company(company)
                .position("Developer")
                .compensation(100000)
                .content("Job description")
                .techStack("Java")
                .build();

        company.setJobPostings(Collections.singletonList(jobPosting));
        when(jobPostingRepository.findById(anyLong())).thenReturn(Optional.of(jobPosting));

        // When
        JobPostingDetailsResponseDto result = jobPostingService.getJobPostingDetails(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Developer", result.getPosition());
        assertEquals(100000, result.getCompensation());
        assertEquals("Job description", result.getContent());
        assertEquals("Java", result.getTechStack());
        assertNotNull(result.getOtherJobPostings());
        assertTrue(result.getOtherJobPostings().isEmpty());
    }

    @Test
    void getJobPostingDetails_notFound() {
        // Given
        when(jobPostingRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(JobPostingNotFoundException.class, () -> jobPostingService.getJobPostingDetails(1L));
    }
}

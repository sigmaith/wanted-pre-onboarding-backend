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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @Mock
    private JobPostingRepository jobPostingRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private JobApplicationService jobApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void applyToJob_success_existingApplication() {
        // Given
        JobPosting jobPosting = new JobPosting();
        Member member = new Member();
        JobApplication jobApplication = JobApplication.builder()
                .jobPosting(jobPosting)
                .member(member)
                .status(JobApplicationStatus.지원하지않음)
                .build();

        JobApplicationRequestDto requestDto = new JobApplicationRequestDto(1L, 1L);

        when(jobPostingRepository.findById(anyLong())).thenReturn(Optional.of(jobPosting));
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
        when(jobApplicationRepository.findByJobPostingAndMember(anyLong(), anyLong())).thenReturn(Optional.of(jobApplication));

        // When
        jobApplicationService.applyToJob(requestDto);

        // Then
        ArgumentCaptor<JobApplication> jobApplicationCaptor = ArgumentCaptor.forClass(JobApplication.class);
        verify(jobApplicationRepository).save(jobApplicationCaptor.capture());
        JobApplication capturedJobApplication = jobApplicationCaptor.getValue();

        assertEquals(JobApplicationStatus.지원완료, capturedJobApplication.getStatus());
        assertEquals(jobPosting, capturedJobApplication.getJobPosting());
        assertEquals(member, capturedJobApplication.getMember());
    }


    @Test
    void applyToJob_success_newApplication() {
        // Given
        JobPosting jobPosting = new JobPosting();
        Member member = new Member();

        JobApplicationRequestDto requestDto = new JobApplicationRequestDto(1L, 1L);

        when(jobPostingRepository.findById(anyLong())).thenReturn(Optional.of(jobPosting));
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
        when(jobApplicationRepository.findByJobPostingAndMember(anyLong(), anyLong())).thenReturn(Optional.empty());

        // When
        jobApplicationService.applyToJob(requestDto);

        // Then
        verify(jobApplicationRepository, times(1)).save(any(JobApplication.class));
    }

    @Test
    void applyToJob_jobPostingNotFound() {
        // Given
        JobApplicationRequestDto requestDto = new JobApplicationRequestDto(1L, 1L);

        when(jobPostingRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(JobPostingNotFoundException.class, () -> jobApplicationService.applyToJob(requestDto));
    }

    @Test
    void applyToJob_memberNotFound() {
        // Given
        JobPosting jobPosting = new JobPosting();
        JobApplicationRequestDto requestDto = new JobApplicationRequestDto(1L, 1L);

        when(jobPostingRepository.findById(anyLong())).thenReturn(Optional.of(jobPosting));
        when(memberRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(MemberNotFoundException.class, () -> jobApplicationService.applyToJob(requestDto));
    }
}
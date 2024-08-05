package com.example.preonboarding.job_posting.controller;

import com.example.preonboarding.job_posting.dto.JobPostingCreateDto;
import com.example.preonboarding.job_posting.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job-postings")
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping
    public ResponseEntity<Void> createJobPosting(@RequestBody JobPostingCreateDto jobPostingCreateDto) {
        jobPostingService.createJobPosting(jobPostingCreateDto);
        return ResponseEntity.ok().build(); // HTTP 200 OK
    }
}

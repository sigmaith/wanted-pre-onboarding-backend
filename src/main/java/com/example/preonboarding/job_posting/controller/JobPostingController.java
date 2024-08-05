package com.example.preonboarding.job_posting.controller;

import com.example.preonboarding.job_posting.dto.JobPostingCreateDto;
import com.example.preonboarding.job_posting.dto.JobPostingUpdateDto;
import com.example.preonboarding.job_posting.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-postings")
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping
    public ResponseEntity<Void> createJobPosting(@RequestBody JobPostingCreateDto jobPostingCreateDto) {
        jobPostingService.createJobPosting(jobPostingCreateDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJobPosting(@PathVariable Long id, @RequestBody JobPostingUpdateDto jobPostingUpdateDto) {
        jobPostingService.updateJobPosting(id, jobPostingUpdateDto);
        return ResponseEntity.ok().build();
    }
}

package com.example.preonboarding.job_posting.controller;
import com.example.preonboarding.job_posting.dto.JobPostingCreateDto;
import com.example.preonboarding.job_posting.dto.JobPostingResponseDto;
import com.example.preonboarding.job_posting.dto.JobPostingUpdateDto;
import com.example.preonboarding.job_posting.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPosting(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<JobPostingResponseDto>> searchJobPostings(@RequestParam(required = false) String search) {
        List<JobPostingResponseDto> jobPostings;
        if (search == null) {
            jobPostings = jobPostingService.getAllJobPostings();
        } else {
            jobPostings = jobPostingService.searchJobPostings(search);
        }
        return ResponseEntity.ok(jobPostings);
    }
}

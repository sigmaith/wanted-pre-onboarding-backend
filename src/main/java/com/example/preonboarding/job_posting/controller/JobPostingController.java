package com.example.preonboarding.job_posting.controller;
import com.example.preonboarding.job_posting.dto.JobPostingCreateDto;
import com.example.preonboarding.job_posting.dto.JobPostingDetailsResponseDto;
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

    /**
     * 채용 공고를 생성합니다.
     *
     * @param jobPostingCreateDto 생성할 채용 공고 정보
     * @return 상태 코드 200 OK
     */
    @PostMapping
    public ResponseEntity<Void> createJobPosting(@RequestBody JobPostingCreateDto jobPostingCreateDto) {
        jobPostingService.createJobPosting(jobPostingCreateDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 채용 공고를 수정합니다.
     *
     * @param id 수정할 채용 공고 ID
     * @param jobPostingUpdateDto 수정할 채용 공고 정보
     * @return 상태 코드 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJobPosting(@PathVariable Long id, @RequestBody JobPostingUpdateDto jobPostingUpdateDto) {
        jobPostingService.updateJobPosting(id, jobPostingUpdateDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 채용 공고를 삭제합니다.
     *
     * @param id 삭제할 채용 공고 ID
     * @return 상태 코드 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPosting(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 모든 채용 공고를 조회하거나 검색된 채용 공고를 조회합니다.
     *
     * @param search 검색어 (선택 사항)
     * @return 채용 공고 목록
     */
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

    /**
     * 채용 공고의 상세 정보를 조회합니다.
     *
     * @param id 조회할 채용 공고 ID
     * @return 채용 공고의 상세 정보
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobPostingDetailsResponseDto> getJobPostingDetails(@PathVariable Long id) {
        JobPostingDetailsResponseDto details = jobPostingService.getJobPostingDetails(id);
        return ResponseEntity.ok(details);
    }
}

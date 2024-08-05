package com.example.preonboarding.job_application.controller;

import com.example.preonboarding.job_application.dto.JobApplicationRequestDto;
import com.example.preonboarding.job_application.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job-application")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    /**
     * 사용자가 채용 공고에 지원합니다.
     *
     * @param jobApplicationRequestDto 지원 요청 DTO (채용 공고 ID와 사용자 ID를 포함)
     * @return 상태 코드 200 OK
     */
    @PostMapping
    public ResponseEntity<Void> applyToJob(@RequestBody JobApplicationRequestDto jobApplicationRequestDto) {
        jobApplicationService.applyToJob(jobApplicationRequestDto);
        return ResponseEntity.ok().build();
    }
}

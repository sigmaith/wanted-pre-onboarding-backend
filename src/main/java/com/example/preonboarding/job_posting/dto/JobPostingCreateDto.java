package com.example.preonboarding.job_posting.dto;

import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.job_posting.domain.JobPosting;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobPostingCreateDto {
    private Long companyId;
    private String position;
    private int compensation;
    private String content;
    private String techStack;

    @Builder
    public JobPostingCreateDto(Long companyId, String position, int compensation, String content, String techStack) {
        this.companyId = companyId;
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.techStack = techStack;
    }

    public static JobPosting toEntity(JobPostingCreateDto jobPostingCreateDto, Company company) {
        return JobPosting.builder()
                .company(company)
                .position(jobPostingCreateDto.getPosition())
                .compensation(jobPostingCreateDto.getCompensation())
                .content(jobPostingCreateDto.getContent())
                .techStack(jobPostingCreateDto.getTechStack())
                .build();
    }
}

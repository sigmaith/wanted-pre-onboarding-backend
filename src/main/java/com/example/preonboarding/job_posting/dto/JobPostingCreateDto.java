package com.example.preonboarding.job_posting.dto;

import com.example.preonboarding.company.domain.Company;
import com.example.preonboarding.job_posting.domain.JobPosting;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobPostingCreateDto {
    @JsonProperty("회사_id")
    private Long companyId;

    @JsonProperty("채용포지션")
    private String position;

    @JsonProperty("채용보상금")
    private int compensation;

    @JsonProperty("채용내용")
    private String content;

    @JsonProperty("사용기술")
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

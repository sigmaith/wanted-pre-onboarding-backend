package com.example.preonboarding.job_posting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class JobPostingResponseDto {
    @JsonProperty("채용공고_id")
    private Long id;

    @JsonProperty("회사명")
    private String companyName;

    @JsonProperty("국가")
    private String country;

    @JsonProperty("지역")
    private String region;

    @JsonProperty("채용포지션")
    private String position;

    @JsonProperty("채용보상금")
    private int compensation;

    @JsonProperty("사용기술")
    private String techStack;

    @Builder
    public JobPostingResponseDto(Long id, String companyName, String country, String region, String position, int compensation, String techStack) {
        this.id = id;
        this.companyName = companyName;
        this.country = country;
        this.region = region;
        this.position = position;
        this.compensation = compensation;
        this.techStack = techStack;
    }

}

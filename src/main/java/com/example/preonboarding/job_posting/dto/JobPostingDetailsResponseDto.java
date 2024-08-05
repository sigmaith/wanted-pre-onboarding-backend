package com.example.preonboarding.job_posting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobPostingDetailsResponseDto {
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

    @JsonProperty("채용내용")
    private String content;

    @JsonProperty("회사가올린다른채용공고")
    private List<Long> otherJobPostings;

    @Builder
    public JobPostingDetailsResponseDto(Long id, String companyName, String country, String region, String position,
                                        int compensation, String techStack, String content, List<Long> otherJobPostings) {
        this.id = id;
        this.companyName = companyName;
        this.country = country;
        this.region = region;
        this.position = position;
        this.compensation = compensation;
        this.techStack = techStack;
        this.content = content;
        this.otherJobPostings = otherJobPostings;
    }
}

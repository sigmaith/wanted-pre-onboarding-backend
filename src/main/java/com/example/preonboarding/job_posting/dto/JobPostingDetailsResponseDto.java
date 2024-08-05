package com.example.preonboarding.job_posting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobPostingDetailsResponseDto {
    private Long id;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private int compensation;
    private String techStack;
    private String content;
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

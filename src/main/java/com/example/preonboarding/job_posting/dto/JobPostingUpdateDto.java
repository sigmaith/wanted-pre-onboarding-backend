package com.example.preonboarding.job_posting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobPostingUpdateDto {
    @JsonProperty("채용포지션")
    private String position;

    @JsonProperty("채용보상금")
    private int compensation;

    @JsonProperty("채용내용")
    private String content;

    @JsonProperty("사용기술")
    private String techStack;

    @Builder
    public JobPostingUpdateDto(String position, int compensation, String content, String techStack) {
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.techStack = techStack;
    }
}

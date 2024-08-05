package com.example.preonboarding.job_posting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobPostingUpdateDto {
    private String position;
    private int compensation;
    private String content;
    private String techStack;

    @Builder
    public JobPostingUpdateDto(String position, int compensation, String content, String techStack) {
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.techStack = techStack;
    }
}

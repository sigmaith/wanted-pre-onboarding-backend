package com.example.preonboarding.job_application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JobApplicationRequestDto {
    @JsonProperty("채용공고_id")
    private Long jobPostingId;

    @JsonProperty("사용자_id")
    private Long memberId;
}

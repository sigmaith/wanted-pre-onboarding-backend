package com.example.preonboarding.job_application.domain;

import com.example.preonboarding.job_posting.domain.JobPosting;
import com.example.preonboarding.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_application_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    @Enumerated(EnumType.STRING)
    private JobApplicationStatus status = JobApplicationStatus.지원하지않음;

    @Builder
    public JobApplication(Member member, JobPosting jobPosting, JobApplicationStatus status) {
        this.member = member;
        this.jobPosting = jobPosting;
        this.status = status;
    }

    public void apply() {
        if (JobApplicationStatus.지원완료.equals(this.status)) {
            throw new IllegalStateException("이미 지원 완료된 상태입니다.");
        }
        this.status = JobApplicationStatus.지원완료;
    }
}

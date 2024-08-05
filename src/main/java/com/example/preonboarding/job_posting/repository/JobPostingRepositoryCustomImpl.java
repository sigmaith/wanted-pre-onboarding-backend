package com.example.preonboarding.job_posting.repository;

import com.example.preonboarding.job_posting.domain.JobPosting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.example.preonboarding.job_posting.domain.QJobPosting.jobPosting;

@RequiredArgsConstructor
@Repository
public class JobPostingRepositoryCustomImpl implements JobPostingRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<JobPosting> searchJobPostings(String search) {
        boolean isNumber = search.chars().allMatch(Character::isDigit);

        return jpaQueryFactory.selectFrom(jobPosting)
                .where(
                        jobPosting.company.name.containsIgnoreCase(search)
                                .or(jobPosting.company.country.containsIgnoreCase(search))
                                .or(jobPosting.company.region.containsIgnoreCase(search))
                                .or(jobPosting.position.containsIgnoreCase(search))
                                .or(jobPosting.techStack.containsIgnoreCase(search))
                                .or(isNumber ? jobPosting.compensation.eq(Integer.parseInt(search)) : null)
                )
                .fetch();
    }
}

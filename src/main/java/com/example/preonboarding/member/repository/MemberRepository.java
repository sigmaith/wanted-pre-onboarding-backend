package com.example.preonboarding.member.repository;

import com.example.preonboarding.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

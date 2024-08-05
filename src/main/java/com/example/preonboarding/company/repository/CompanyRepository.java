package com.example.preonboarding.company.repository;

import com.example.preonboarding.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}

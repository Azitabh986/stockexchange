package com.cts.stockexchange.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.cts.stockexchange.entity.CompanyDetails;

public interface CompanyRepository extends JpaRepository<CompanyDetails, Long> {

}

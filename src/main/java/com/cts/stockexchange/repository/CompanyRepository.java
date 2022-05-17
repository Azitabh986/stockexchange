package com.cts.stockexchange.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.stockexchange.entity.CompanyDetails;

public interface CompanyRepository extends JpaRepository<CompanyDetails, Long> {
	@Query(value = "Select * from company_details where code=?1",nativeQuery = true)
	CompanyDetails findByCode(String code);

}

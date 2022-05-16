package com.cts.stockexchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.stockexchange.entity.CompanyDetails;
import com.cts.stockexchange.repository.CompanyRepository;

@RestController
@RequestMapping("/api/v1.0")
public class CompanyResource {
	@Autowired
	private CompanyRepository companyRepo;
	@PostMapping("/market/company/register")
	public ResponseEntity<CompanyDetails> saveCompanyDetails(@RequestBody CompanyDetails company){
		companyRepo.save(company);
		return new ResponseEntity<>(company,HttpStatus.CREATED);
	}
}

package com.cts.stockexchange.controller;

import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
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
	
	private final static Logger logger=org.slf4j.LoggerFactory.getLogger(CompanyResource.class);
	@Autowired
	private CompanyRepository companyRepo;
	@PostMapping("/market/company/register")
	public ResponseEntity<CompanyDetails> saveCompanyDetails(@Valid @RequestBody CompanyDetails company){
		logger.info("See info:- "+company.toString());
		companyRepo.save(company);
		
		return new ResponseEntity<>(company,HttpStatus.CREATED);
	}
}

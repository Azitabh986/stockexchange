package com.cts.stockexchange.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.stockexchange.entity.CompanyDetails;
import com.cts.stockexchange.entity.StockPrice;
import com.cts.stockexchange.repository.CompanyRepository;
import com.cts.stockexchange.repository.StockPriceRepository;
import com.cts.stockexchange.service.CompanyService;

@RestController
@RequestMapping("/api/v1.0")
public class CompanyResource {
	
	private final static Logger logger=org.slf4j.LoggerFactory.getLogger(CompanyResource.class);

	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/market/company/register")
	public ResponseEntity<CompanyDetails> saveCompanyDetails(@Valid @RequestBody CompanyDetails company){
		logger.info("See info:- "+company.toString());
		
		
		return companyService.saveCompDetails(company);
	}
	
	@PostMapping("/market/stock/add/{companyCode}")
	public ResponseEntity<String> addStockPrice(@Valid @PathVariable String companyCode,@RequestBody StockPrice stockPrice){
		return companyService.addStockPrices(companyCode,stockPrice);
	}
	
	@DeleteMapping("/market/stock/delete/{companyCode}")
	public ResponseEntity<String> deleteCompanyDetails(@Valid @PathVariable String companyCode){
		return companyService.deleteCompanyInfo(companyCode);
	}
	
	@GetMapping("/market/company/getall")
	public ResponseEntity<List<CompanyDetails>> getAllData(){
		return companyService.retrieveAllCompanyDetails();
	}
}

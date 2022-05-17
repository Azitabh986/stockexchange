package com.cts.stockexchange.controller;

import java.util.Date;

import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.stockexchange.entity.CompanyDetails;
import com.cts.stockexchange.entity.StockPrice;
import com.cts.stockexchange.repository.CompanyRepository;
import com.cts.stockexchange.repository.StockPriceRepository;

@RestController
@RequestMapping("/api/v1.0")
public class CompanyResource {
	
	private final static Logger logger=org.slf4j.LoggerFactory.getLogger(CompanyResource.class);
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private StockPriceRepository stockPriceRepo;
	
	@PostMapping("/market/company/register")
	public ResponseEntity<CompanyDetails> saveCompanyDetails(@Valid @RequestBody CompanyDetails company){
		logger.info("See info:- "+company.toString());
		companyRepo.save(company);
		
		return new ResponseEntity<>(company,HttpStatus.CREATED);
	}
	
	@PostMapping("/market/stock/add/{companyCode}")
	public ResponseEntity<String> addStockPrice(@Valid @PathVariable String companyCode,@RequestBody StockPrice stockPrice){
		stockPrice.setUpdatedTime(new Date());
		CompanyDetails cd=companyRepo.findByCode(companyCode);
		if(cd==null)
			throw new RuntimeException("Company doesn't Exits.");
		stockPrice.setCompanyDetails(cd);
		stockPriceRepo.save(stockPrice);
		return new ResponseEntity<String>("For "+companyCode+" is inserted the stock price.",HttpStatus.CREATED);
	}
	
	@DeleteMapping("/market/stock/delete/{companyCode}")
	public ResponseEntity<String> deleteCompanyDetails(@Valid @PathVariable String companyCode){
		CompanyDetails cd=companyRepo.findByCode(companyCode);
		if(cd==null)
			throw new RuntimeException("Company doesn't Exits.");
		companyRepo.deleteById(cd.getId());
		return  new ResponseEntity<String>("For "+companyCode+" is deleted.",HttpStatus.OK);
	}
}

package com.cts.stockexchange.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.stockexchange.entity.CompanyDetails;
import com.cts.stockexchange.entity.StockPrice;
import com.cts.stockexchange.repository.CompanyRepository;
import com.cts.stockexchange.repository.StockPriceRepository;

@Service
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private StockPriceRepository stockPriceRepo;

	public ResponseEntity<CompanyDetails> saveCompDetails(CompanyDetails company) {
		companyRepo.save(company);
		return new ResponseEntity<CompanyDetails>(company,HttpStatus.CREATED);
	}

	public ResponseEntity<String> addStockPrices(String companyCode, StockPrice stockPrice) {
		stockPrice.setUpdatedTime(new Date());
		CompanyDetails cd=companyRepo.findByCode(companyCode);
		if(cd==null)
			throw new RuntimeException("Company doesn't Exits.");
		stockPrice.setCompanyDetails(cd);
		stockPriceRepo.save(stockPrice);
		return new ResponseEntity<String>("For "+companyCode+" is inserted the stock price.",HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteCompanyInfo( String companyCode) {
		CompanyDetails cd=companyRepo.findByCode(companyCode);
		if(cd==null)
			throw new RuntimeException("Company doesn't Exits.");
		companyRepo.deleteById(cd.getId());
		return  new ResponseEntity<String>("For "+companyCode+" is deleted.",HttpStatus.OK);
	}

	public ResponseEntity<List<CompanyDetails>> retrieveAllCompanyDetails() {
		List<CompanyDetails> cd= companyRepo.findAll();
		return new ResponseEntity<List<CompanyDetails>>(cd,HttpStatus.OK);
	}



	

}

package com.cts.stockexchange.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

	public ResponseEntity<?> addStockPrices(String companyCode, StockPrice stockPrice) {
		stockPrice.setUpdatedTime(new Date());
		CompanyDetails cd=companyRepo.findByCode(companyCode);
		if(cd==null)
			throw new RuntimeException("Company doesn't Exits.");
		stockPrice.setCompanyDetails(cd);
		stockPriceRepo.save(stockPrice);
		return new ResponseEntity<>(cd,HttpStatus.CREATED);
	}

	public ResponseEntity<?> deleteCompanyInfo( String companyCode) {
		CompanyDetails cd=companyRepo.findByCode(companyCode);
		if(cd==null)
			throw new RuntimeException("Company doesn't Exits.");
		companyRepo.deleteById(cd.getId());
		return  new ResponseEntity<>(cd,HttpStatus.OK);
	}

	public ResponseEntity<List<CompanyDetails>> retrieveAllCompanyDetails() {
		List<CompanyDetails> cd= companyRepo.findAll();
		return new ResponseEntity<List<CompanyDetails>>(cd,HttpStatus.OK);
	}

	public ResponseEntity<?> getByCompanyCode(String companycode) {
		CompanyDetails cd=companyRepo.findByCode(companycode);
		if(cd==null)
			throw new RuntimeException("Company doesn't Exits.");
		return new ResponseEntity<Object>(cd,HttpStatus.OK);
	}

	public ResponseEntity<List<StockPrice>> getCompanyDetailsByCodeAndDate(String companycode, Date startdate,
			Date enddate) {
		List<StockPrice> filterSP;
		List<CompanyDetails> companyDetails=(List<CompanyDetails>) companyRepo.findAll();
		List<CompanyDetails> cd=companyDetails.stream().filter(i->i.getCode().equals(companycode)).collect(Collectors.toList());
		if(cd.size()>0) {
			List<StockPrice> stockPrices=cd.get(0).getStockPrice();
			filterSP=stockPrices.stream()
					.filter(i->i.getUpdatedTime().after(startdate) && i.getUpdatedTime().before(enddate) ).collect(Collectors.toList());
		}else {
			throw new RuntimeException("Company doesn't exists!");
		}
		return new ResponseEntity<List<StockPrice>>(filterSP,HttpStatus.OK);
	}



	

}

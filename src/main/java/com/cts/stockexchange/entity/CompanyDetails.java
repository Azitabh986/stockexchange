package com.cts.stockexchange.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


import com.cts.stockexchange.enumValues.StockExchange;

@Entity
@Table(name="company_details")
public class CompanyDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(unique = true)
	@NotBlank(message = "Company Code is mandatory")
	private String code;
	@NotBlank(message = "Company name is mandatory")
	private String name;
	@NotBlank(message = "CEO is mandatory")
	private String ceo;
	
	@Min(value = 100000000,message = "Minimum turn over must be 10CR")
	private float trunOver;
	@NotBlank(message = "Company website is mandatory")
	private String website;
	
	private StockExchange stockExchange;
	
	@OneToMany(mappedBy = "companyDetails", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<StockPrice> stockPrice;
	
	public CompanyDetails() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public float getTrunOver() {
		return trunOver;
	}

	public void setTrunOver(float trunOver) {
		this.trunOver = trunOver;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public StockExchange getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}
	
	
	public List<StockPrice> getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(List<StockPrice> stockPrice) {
		this.stockPrice = stockPrice;
	}

	@Override
	public String toString() {
		return "CompanyDetails [id=" + id + ", code=" + code + ", name=" + name + ", ceo=" + ceo + ", trunOver="
				+ trunOver + ", website=" + website + ", stockExchange=" + stockExchange + ", stockPrice=" + stockPrice
				+ "]";
	}

	
	
	
}

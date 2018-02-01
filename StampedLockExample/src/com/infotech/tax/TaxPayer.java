package com.infotech.tax;

public class TaxPayer {
	
	private String taxPayerName;
	private String taxPayerSsn;
	private double taxAmount;

	public String getTaxPayerName() {
		return taxPayerName;
	}

	public void setTaxPayerName(String taxPayerName) {
		this.taxPayerName = taxPayerName;
	}

	public String getTaxPayerSsn() {
		return taxPayerSsn;
	}

	public void setTaxPayerSsn(String taxPayerSsn) {
		this.taxPayerSsn = taxPayerSsn;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
}

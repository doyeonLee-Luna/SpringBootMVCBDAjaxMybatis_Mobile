package com.mycom.myapp.dto;

public class SaleDto {

	private int saleid;
	private int custid;
	private int mobileid;
	private int quantity;
	private double totalPrice;
	private String saleDate;
	
	public SaleDto() {}
	

	public SaleDto(int saleid, int custid, int mobileid, int quantity, double totalPrice, String saleDate) {
		super();
		this.saleid = saleid;
		this.custid = custid;
		this.mobileid = mobileid;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.saleDate = saleDate;
	}


	public int getSaleid() {
		return saleid;
	}

	public void setSaleid(int saleid) {
		this.saleid = saleid;
	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public int getMobileid() {
		return mobileid;
	}

	public void setMobileid(int mobileid) {
		this.mobileid = mobileid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}


	@Override
	public String toString() {
		return "SaleDto [saleid=" + saleid + ", custid=" + custid + ", mobileid=" + mobileid + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + ", saleDate=" + saleDate + "]";
	}
	
	
	
	
	
}

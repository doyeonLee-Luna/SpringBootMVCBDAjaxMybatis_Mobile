package com.mycom.myapp.dto;

public class MobileDto {
    
    private int mobileid;
    private String brand;
    private String model;
    private int price;
    private int stock;
    private String mobileType;
    
    public MobileDto() {}
    
	public MobileDto(int mobileid, String brand, String model, int price, int stock, String mobileType) {
		super();
		this.mobileid = mobileid;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.stock = stock;
		this.mobileType = mobileType;
	}

	public int getMobileid() {
		return mobileid;
	}
	public void setMobileid(int mobileid) {
		this.mobileid = mobileid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getMobileType() {
		return mobileType;
	}
	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}
	
	@Override
	public String toString() {
		return "MobileDto [mobileid=" + mobileid + ", brand=" + brand + ", model=" + model + ", price=" + price
				+ ", stock=" + stock + ", mobileType=" + mobileType + "]";
	}
}
    
	
 
package com.mycom.myapp.dto;

public class CustomerDto {
    
    private int custid;
    private String name;
    private String address;
    private String phone;
    private String customerType;
    
    public CustomerDto() {}

	public CustomerDto(int custid, String name, String address, String phone, String customerType) {
		super();
		this.custid = custid;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.customerType = customerType;
	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "CustomerDto [custid=" + custid + ", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", customerType=" + customerType + "]";
	}
}
   
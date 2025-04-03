package com.mycom.myapp.service;

import java.util.List;
import java.util.Map;

import com.mycom.myapp.dto.CustomerDto;

// service layer 는 실무에서는 가장 복잡한 코드가 존재하고 있음 <= Business Logic을 구현하고 있기 때문
// 단순 CRUD 는 controller 와 repository layer 중간에서 단순 중계 역할을 한다(pass)
public interface CustomerService {
	// emp-mapper 1 대응 
    List<CustomerDto> listCustomer();
    CustomerDto detailCustomer(int custid);
    int insertCustomer(CustomerDto customer);
    int updateCustomer(CustomerDto customer);
    int deleteCustomer(int custid);
    
	// emp-mapper 2 대응
    List<CustomerDto> listCustomerLike(String searchWord);
    List<CustomerDto> listCustomerMap();
    List<CustomerDto> listCustomerWhereIf(Map<String, String> map);
}

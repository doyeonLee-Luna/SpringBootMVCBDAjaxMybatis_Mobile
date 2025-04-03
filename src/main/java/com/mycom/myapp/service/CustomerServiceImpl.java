package com.mycom.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mycom.myapp.dao.CustomerDao;
import com.mycom.myapp.dto.CustomerDto;

@Service // Service layer 의 기본 구현체 // 실제 클래스에 붙는다 인터페이스에 붙지 않는다.
public class CustomerServiceImpl implements CustomerService{

//	@Autowired // 이렇게도 가능 함
//	BookDao bookDao;
	
	// 생성자 방식으로 생성
	private final CustomerDao customerDao;
	
	public CustomerServiceImpl(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	


	@Override
	public List<CustomerDto> listCustomer() {
		// TODO Auto-generated method stub
		return customerDao.listCustomer();
	}

	@Override
	public CustomerDto detailCustomer(int custid) {
		// TODO Auto-generated method stub
		return customerDao.detailCustomer(custid);
	}

	@Override
	public int insertCustomer(CustomerDto customer) {
		// TODO Auto-generated method stub
		return customerDao.insertCustomer(customer);
	}

	@Override
	public int updateCustomer(CustomerDto customer) {
		// TODO Auto-generated method stub
		return customerDao.updateCustomer(customer);
	}

	@Override
	public int deleteCustomer(int custid) {
		// TODO Auto-generated method stub
		return customerDao.deleteCustomer(custid);
	}


	 // emp-mapper-2.xml 대응
	@Override
	public List<CustomerDto> listCustomerLike(String searchWord) {
		return customerDao.listCustomerLike(searchWord);
	}



	@Override
	public List<CustomerDto> listCustomerMap() {
		return customerDao.listCustomerMap();
	}



	@Override
	public List<CustomerDto> listCustomerWhereIf(Map<String, String> map) {
		return customerDao.listCustomerWhereIf(map);
	}

}

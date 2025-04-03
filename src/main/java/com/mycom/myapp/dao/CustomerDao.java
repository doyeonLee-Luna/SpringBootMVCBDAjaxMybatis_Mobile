package com.mycom.myapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.dto.CustomerDto;

//  crud
@Mapper
public interface CustomerDao {
    List<CustomerDto> listCustomer();
    CustomerDto detailCustomer(int custid);
    int insertCustomer(CustomerDto customer);
    int updateCustomer(CustomerDto customer);
    int deleteCustomer(int custid);
    
    List<CustomerDto> listCustomerLike(String searchWord);
    List<CustomerDto> listCustomerMap();
    List<CustomerDto> listCustomerWhereIf(Map<String, String> map);
}

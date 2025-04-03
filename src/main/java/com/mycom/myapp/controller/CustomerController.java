package com.mycom.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.dto.CustomerDto;
import com.mycom.myapp.service.CustomerService;

@Controller
@ResponseBody
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    // 고객 목록 조회
    @GetMapping("/list")

    public List<CustomerDto> listCustomer() {
        return customerService.listCustomer();
    }
    
    // 고객 상세 조회
    @GetMapping("/detail/{custid}")
    public CustomerDto detailCustomer(@PathVariable("custid") int custid) {
        return customerService.detailCustomer(custid);
    }
    
    // 고객 등록
    @PostMapping("/insert")

    public Map<String, String> insertCustomer(CustomerDto customerDto) {

        int ret = customerService.insertCustomer(customerDto);      
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        
        return map;
    }
    
    // 고객 정보 수정
    @PostMapping("/update")
    public Map<String, String> updateCustomer(CustomerDto customerDto) {

        int ret = customerService.updateCustomer(customerDto);
        
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        
        return map;
    }
    
    // 고객 삭제
    @GetMapping("/delete/{custid}")
    public Map<String, String> deleteCustomer(@PathVariable("custid") int custid) {

        int ret = customerService.deleteCustomer(custid);
        
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        
        return map;
    }
    
    // custoemr-mapper-2.xml 
    @GetMapping("/listCustomerLike")
    public List<CustomerDto> listCustomerLike(@RequestParam String searchWord) {
    	return customerService.listCustomerLike(searchWord);
    }
    
    @GetMapping("/listCustomerMap")
    public List<CustomerDto> listCustomerMap() {
    	return customerService.listCustomerMap();
    }
    
    @GetMapping("/listCustomerWhereIf")
    public List<CustomerDto> listCustomerWhereIf(@RequestParam Map<String, String> map) {
    	return customerService.listCustomerWhereIf(map);
    }
}

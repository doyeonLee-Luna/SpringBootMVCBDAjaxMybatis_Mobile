package com.mycom.myapp.service;

import java.util.List;
import java.util.Map;

import com.mycom.myapp.dto.SaleDto;

// Service Layer: 비즈니스 로직을 담당
public interface SaleService {
    List<SaleDto> listSale(); // 전체 판매 목록 조회
    SaleDto detailSale(int saleid); // 특정 판매 상세 조회
    int insertSale(SaleDto sale); // 판매 등록
    int updateSale(SaleDto sale); // 판매 정보 수정
    int deleteSale(int saleid); // 판매 삭제
    
    List<SaleDto> listSaleByCustomer(int custid); // 특정 고객의 판매 목록
    List<SaleDto> listSaleByMobile(int mobileid); // 특정 핸드폰의 판매 목록
    List<SaleDto> listSaleWhereIf(Map<String, String> map); // 조건별 판매 목록 조회
    

}

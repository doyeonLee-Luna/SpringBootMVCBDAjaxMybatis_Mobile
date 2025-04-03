package com.mycom.myapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.dto.SaleDto;

// CRUD
@Mapper
public interface SaleDao {
    List<SaleDto> listSale();
    SaleDto detailSale(int saleid);
    int insertSale(SaleDto sale);
    SaleDto getSaleById(int saleId);
    int updateSale(SaleDto sale);
    int deleteSale(int saleid);
    

    
    List<SaleDto> listSaleByCustomer(int custid);
    List<SaleDto> listSaleByMobile(int mobileid);
    List<SaleDto> listSaleWhereIf(Map<String, String> map);
    


}

package com.mycom.myapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.mycom.myapp.dto.MobileDto;


// CRUD
@Mapper
public interface MobileDao {
    List<MobileDto> listMobile();
    MobileDto detailMobile(int mobileid);
    int insertMobile(MobileDto mobile);
    int updateMobile(MobileDto mobile);
    int deleteMobile(int mobileid);
    

    
    List<MobileDto> listMobileLike(String searchWord);
    List<MobileDto> listMobileMap();
    List<MobileDto> listMobileWhereIf(Map<String, String> map);
    
    @Update("UPDATE mobile SET stock = stock - #{quantity} WHERE mobileid = #{mobileid}")
    int updateStockAfterSale(int mobileid, int quantity);
}
    




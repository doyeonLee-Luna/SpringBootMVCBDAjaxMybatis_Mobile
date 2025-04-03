package com.mycom.myapp.service;

import java.util.List;
import java.util.Map;

import com.mycom.myapp.dto.MobileDto;

// Service Layer: Business Logic을 구현하는 부분
public interface MobileService {
    List<MobileDto> listMobile();
    MobileDto detailMobile(int mobileid);
    int insertMobile(MobileDto mobile);
    int updateMobile(MobileDto mobile);
    int deleteMobile(int mobileid);
    
    List<MobileDto> listMobileLike(String searchWord);
    List<MobileDto> listMobileMap();
    List<MobileDto> listMobileWhereIf(Map<String, String> map);
}

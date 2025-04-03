package com.mycom.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mycom.myapp.dao.MobileDao;
import com.mycom.myapp.dto.MobileDto;

@Service // Service layer의 기본 구현체
public class MobileServiceImpl implements MobileService {

    private final MobileDao mobileDao;
    
    public MobileServiceImpl(MobileDao mobileDao) {
        this.mobileDao = mobileDao;
    }

    @Override
    public List<MobileDto> listMobile() {
        return mobileDao.listMobile();
    }

    @Override
    public MobileDto detailMobile(int mobileid) {
        return mobileDao.detailMobile(mobileid);
    }

    @Override
    public int insertMobile(MobileDto mobile) {
        return mobileDao.insertMobile(mobile);
    }

    @Override
    public int updateMobile(MobileDto mobile) {
        return mobileDao.updateMobile(mobile);
    }

    @Override
    public int deleteMobile(int mobileid) {
        return mobileDao.deleteMobile(mobileid);
    }

 // emp-mapper-2.xml 대응
    @Override
    public List<MobileDto> listMobileLike(String searchWord) {
        return mobileDao.listMobileLike(searchWord);
    }

    @Override
    public List<MobileDto> listMobileMap() {
        return mobileDao.listMobileMap();
    }

    @Override
    public List<MobileDto> listMobileWhereIf(Map<String, String> map) {
        return mobileDao.listMobileWhereIf(map);
    }

}

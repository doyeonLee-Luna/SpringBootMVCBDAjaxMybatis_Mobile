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

import com.mycom.myapp.dto.MobileDto;
import com.mycom.myapp.service.MobileService;

@Controller
@ResponseBody
@RequestMapping("/mobiles")
public class MobileController {

    private final MobileService mobileService;
    
    public MobileController(MobileService mobileService) {
        this.mobileService = mobileService;
    }
    
    // 핸드폰 목록 조회
    @GetMapping("/list")
    public List<MobileDto> listMobile() {
        return mobileService.listMobile();
    }
    
    // 핸드폰 상세 조회
    @GetMapping("/detail/{mobileid}")
    public MobileDto detailMobile(@PathVariable("mobileid") int mobileid) {
        return mobileService.detailMobile(mobileid);
    }
    
    // 핸드폰 등록
    @PostMapping("/insert")
    public Map<String, String> insertMobile(MobileDto mobileDto) {
        int ret = mobileService.insertMobile(mobileDto);      
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }
    
    // 핸드폰 정보 수정
    @PostMapping("/update")
    public Map<String, String> updateMobile(MobileDto mobileDto) {
        int ret = mobileService.updateMobile(mobileDto);
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }
    
    // 핸드폰 삭제
    @GetMapping("/delete/{mobileid}")
    public Map<String, String> deleteMobile(@PathVariable("mobileid") int mobileid) {
        int ret = mobileService.deleteMobile(mobileid);
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }
    
    // mobile-mapper-2.xml 
    @GetMapping("/listMobileLike")
    public List<MobileDto> listMobileLike(@RequestParam String searchWord) {
        return mobileService.listMobileLike(searchWord);
    }
    
    @GetMapping("/listMobileMap")
    public List<MobileDto> listMobileMap() {
        return mobileService.listMobileMap();
    }
    
    @GetMapping("/listMobileWhereIf")
    public List<MobileDto> listMobileWhereIf(@RequestParam Map<String, String> map) {
        return mobileService.listMobileWhereIf(map);
    }
}

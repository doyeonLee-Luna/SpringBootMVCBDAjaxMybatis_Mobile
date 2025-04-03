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

import com.mycom.myapp.dto.SaleDto;
import com.mycom.myapp.service.SaleService;

@Controller
@ResponseBody
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // 판매 목록 조회
    @GetMapping("/list")
    public List<SaleDto> listSale() {
        List<SaleDto> sales = saleService.listSale();
        System.out.println("Sale List: " + sales);
        return sales;
    }

    // 판매 상세 조회
    @GetMapping("/detail/{saleid}")
    public SaleDto detailSale(@PathVariable("saleid") int saleid) {
        return saleService.detailSale(saleid);
    }

    // 판매 등록
    @PostMapping("/insert")
    public Map<String, String> insertSale(SaleDto saleDto) {
        int ret = saleService.insertSale(saleDto);
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }
    
    // 판매 정보 수정 (수량 변경 시 가격 자동 계산)
    @PostMapping("/update")
    public Map<String, String> updateSale(SaleDto saleDto) {

        // 업데이트 수행
        int ret = saleService.updateSale(saleDto);
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }

    

    // 판매 삭제
    @GetMapping("/delete/{saleid}")
    public Map<String, String> deleteSale(@PathVariable("saleid") int saleid) {
        int ret = saleService.deleteSale(saleid);
        Map<String, String> map = new HashMap<>();
        if (ret == 1) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }

    // 특정 고객의 판매 내역 조회
    @GetMapping("/customer/{custid}")
    public List<SaleDto> listSaleByCustomer(@PathVariable("custid") int custid) {
        return saleService.listSaleByCustomer(custid);
    }

    // 특정 핸드폰의 판매 내역 조회
    @GetMapping("/mobile/{mobileid}")
    public List<SaleDto> listSaleByMobile(@PathVariable("mobileid") int mobileid) {
        return saleService.listSaleByMobile(mobileid);
    }

    // 조건별 판매 내역 조회
    @GetMapping("/search")
    public List<SaleDto> listSaleWhereIf(@RequestParam Map<String, String> params) {
        return saleService.listSaleWhereIf(params);
    }
}

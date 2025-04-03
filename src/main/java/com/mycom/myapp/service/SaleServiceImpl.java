package com.mycom.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;  // 트랜잭션 관리 추가

import com.mycom.myapp.dao.SaleDao;
import com.mycom.myapp.dao.MobileDao;  // MobileDao 추가 (필요한 경우)
import com.mycom.myapp.dto.SaleDto;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleDao saleDao;
    private final MobileDao mobileDao;  // MobileDao 의존성 추가

    public SaleServiceImpl(SaleDao saleDao, MobileDao mobileDao) {
        this.saleDao = saleDao;
        this.mobileDao = mobileDao;  // MobileDao 초기화
    }

    @Override
    public List<SaleDto> listSale() {
        return saleDao.listSale();
    }

    @Override
    public SaleDto detailSale(int saleid) {
        return saleDao.detailSale(saleid);
    }

    @Override
    @Transactional  // 트랜잭션을 사용하여, 판매 등록 후 재고 업데이트도 함께 처리
    public int insertSale(SaleDto sale) {
        // 1. 판매 등록
        int ret = saleDao.insertSale(sale);

        // 2. 판매 성공 시 재고 변경
        if (ret == 1) {
            // 판매된 수량만큼 재고 차감
            int updatedStock = mobileDao.updateStockAfterSale(sale.getMobileid(), sale.getQuantity());
            if (updatedStock == 0) {
                // 재고 업데이트 실패 시, 실패 메시지 반환
                System.out.println("재고 업데이트 실패");
                return 0; // 실패 처리
            }
        }
        return ret; // 성공 시 판매 등록 반환
    }

    @Override
    public int updateSale(SaleDto sale) {
        return saleDao.updateSale(sale);
    }

    @Override
    public int deleteSale(int saleid) {
        return saleDao.deleteSale(saleid);
    }

    @Override
    public List<SaleDto> listSaleByCustomer(int custid) {
        return saleDao.listSaleByCustomer(custid);
    }

    @Override
    public List<SaleDto> listSaleByMobile(int mobileid) {
        return saleDao.listSaleByMobile(mobileid);
    }

    @Override
    public List<SaleDto> listSaleWhereIf(Map<String, String> map) {
        return saleDao.listSaleWhereIf(map);
    }
}

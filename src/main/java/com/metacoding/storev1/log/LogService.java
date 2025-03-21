package com.metacoding.storev1.log;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metacoding.storev1.log.LogResponse.ListPage;
import com.metacoding.storev1.store.Store;
import com.metacoding.storev1.store.StoreRepository;

@Service
public class LogService {

    private LogRepository logRepository;
    private StoreRepository storeRepository;

    public LogService(LogRepository logRepository, StoreRepository storeRepository) {
        this.logRepository = logRepository;
        this.storeRepository = storeRepository;
    }

    public List<ListPage> 구매목록() {
        return logRepository.findAllJoinStore();
    }

    @Transactional
    public void 구매하기(int storeId, String buyer, int qty) {
        // 1. 상품 재고 업데이트 (조회, 업데이트)
        Store store = storeRepository.findById(storeId);
        store.재고감소(qty);
        storeRepository.updateById(store.getId(), store.getName(), store.getStock(), store.getPrice());

        // 2. 구매 기록 하기
        logRepository.save(storeId, qty, qty * store.getPrice(), buyer);
    }

}
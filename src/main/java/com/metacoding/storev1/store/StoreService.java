package com.metacoding.storev1.store;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreService {

    private StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    // 3번 : board 프로젝트의 BoardService 참고
    @Transactional // insert, delete, update시에 사용 : 함수 종료시 commit 됨
    public void 상품삭제(int id) {
        // 1. 상품 있어?
        Store store = storeRepository.findById(id);

        if (store == null)
            throw new RuntimeException("상품없어");

        // 3. 삭제
        storeRepository.deleteByid(id); // write (DML = insert, delete, update)
    }

    @Transactional
    public void 상품등록(String name, int stock, int price) {
        storeRepository.save(name, stock, price);
    }

    public List<Store> 상품목록() {
        List<Store> storeList = storeRepository.findAll();
        return storeList;
    }

    public Store 상세보기(int id) {
        Store store = storeRepository.findById(id);
        return store;
    }

    @Transactional
    public void 상품수정(int id, String name, int stock, int price) {
        // 1. 상품 조회
        Store store = storeRepository.findById(id);

        // 2. 없으면 예외 터트리기
        if (store == null)
            throw new RuntimeException("상품없어");

        // 3. 상품 수정
        storeRepository.updateById(id, name, stock, price);
    }
}

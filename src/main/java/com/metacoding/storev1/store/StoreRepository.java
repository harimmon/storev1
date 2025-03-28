package com.metacoding.storev1.store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 데이터베이스랑 연결된 클래스 라고 알려주는 역할
public class StoreRepository {

    // EntityManager(엔터티 매니저) → 데이터베이스랑 소통하는 도구 (과일 정보를 가져오거나 저장할 때 필요)
    private EntityManager em;

    public StoreRepository(EntityManager em) {
        this.em = em;
    }

    // 1번 : board 프로젝트의 BoardRepository 참고
    public void deleteByid(int id) {
        Query query = em.createNativeQuery("delete from store_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public void save(String name, int stock, int price) {
        Query query = em.createNativeQuery("insert into store_tb(name, stock, price) values(?,?,?)");
        query.setParameter(1, name);
        query.setParameter(2, stock);
        query.setParameter(3, price);
        query.executeUpdate();
    }

    public Store findById(int id) {
        Query query = em.createNativeQuery("select * from store_tb where id = ?", Store.class);
        query.setParameter(1, id);
        try {
            return (Store) query.getSingleResult();
        } catch (Exception e) { // NoResultException
            return null;
        }
    }

    public List<Store> findAll() {
        // 조건 : 오브젝트 매핑은 @Entity가 붙어야지만 가능하다. (디폴트 생성자를 호출)
        Query query = em.createNativeQuery("select * from store_tb order by id desc", Store.class);
        return query.getResultList();
    }

    public void updateById(int id, String name, int stock, int price) {
        Query query = em.createNativeQuery("update store_tb set name = ?, stock = ?, price=? where id = ?");
        query.setParameter(1, name);
        query.setParameter(2, stock);
        query.setParameter(3, price);
        query.setParameter(4, id);
        query.executeUpdate();
    }
}

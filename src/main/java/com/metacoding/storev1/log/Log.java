package com.metacoding.storev1.log;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // JPA가 ObjectMapping을 위해 new할 때 사용함.
@Table(name = "log_tb")
@Entity // 설정파일에서 테이블을 생성해준다.
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer storeId; // 상품 ID (FK)
    private Integer qty; // 구매 개수
    private Integer totalPrice; // qty * store(price)
    private String buyer; // 구매자 이름
}

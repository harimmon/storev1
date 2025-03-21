package com.metacoding.storev1.log;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.metacoding.storev1.log.LogResponse.ListPage;

@Import(LogRepository.class)
@DataJpaTest // em IoC 등록
public class LogRepositoryTest {

    @Autowired // 이거 붙이면 DI 해줌, DI해주는 새로운 어노테이션
    private LogRepository logRepository;

    @Test // 세모버튼 생김
        public void findAllJoinStore_test(){ // 매개변수에 아무것도 적을 수 없다.
            List<LogResponse.ListPage>logList = logRepository.findAllJoinStore();
            for (ListPage listPage : logList) {
                System.out.println(listPage);
            }
        }    
}

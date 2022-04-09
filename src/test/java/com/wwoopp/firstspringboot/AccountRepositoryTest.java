package com.wwoopp.firstspringboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void 계정_생성후_조회해보기() {
        Account test = Account.builder().username("test").password("1234").build();
        test = this.accountRepository.save(test);

        assertThat(accountRepository.findById(test.getId()))
                .isPresent()
                .get()
                .isEqualTo(test);
    }

}

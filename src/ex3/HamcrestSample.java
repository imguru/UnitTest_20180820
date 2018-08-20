package ex3;

// Hamcrest = 비교 표현의 확장 라이브러리
//  : jMock 라이브러리 제작자들이 참여하고 만들고 있는 Matcher 라이브러리
//  => 단언문을 문장으로 구성할 수 있도록 해준다.

// Hamcrest 라이브러리는 초창기 jMock의 일부로 포함되어 있던 API에 불과했지만
// 그 유용성을 인정 받아 독립했다.
//  => JUnit4.4 이후로 기본적으로 포함되어 있습니다.
//  => JUnit5 에서는 포함되지 않았습니다.

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

// Javascript - Unit Test
//  : mocha, jest

public class HamcrestSample {

    // xUnit Test Framework 특징
    // - 테스트 메소드 안에서 하나의 단언문이 실패한다면, 이후의 단언문은 수행되지 않는다.
    //  => '죽은 단언문 문제'
    //    : 하나의 테스트 메소드 안에서 너무 많은 단언문을 사용할 경우, 발생하는 문제
    //  => Junit 5 에서는 EXPECT와 비슷하게 사용할 수 있는 기능이 추가되었습니다.
    @Test
    public void balanceTest() {
        Bank bank = new Bank();

        // JUnit 4
        assertEquals(100, bank.getBalance());

        // Hamcrest Library
        assertThat(bank.getBalance(), is(equalTo(100)));
        // Assert that bank.getBalance() is equal to 100
    }

    @Test
    public void createTest() {
        Bank bank = new Bank();

        // assertNotNull(bank.create());

        assertThat(bank.create(), is(notNullValue()));
    }

    @Test
    public void accountTest() {
        Bank bank = new Bank();

        assertTrue(bank.getAccountName().contains("guest"));

        assertThat(bank.getAccountName(), containsString("guest"));
    }
}

// SUT
class Bank {
    public int getBalance() {
        return 0;
    }

    public Bank create() {
        return null;
    }

    public String getAccountName() {
        return "";
    }
}








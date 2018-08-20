package com.lge.utils.test;

// 테스트를 구성하는 패턴(xUnit Test Pattern)
//  : Testcase class per class pattern
// 방법: 하나의 SUT 클래스에 대한 테스트 메소드를 하나의 테스트 케이스 클래스 안에
//      전부 집어 넣는다.

// Java: JUnit 4/5

// JUnit 5: Java 8 이후 버전 지원

import com.lge.utils.Calculator;
import org.junit.Test;

import static org.junit.Assert.*;

// public class CalculatorTest extends TestCase  - JUnit 3
// class CalculatorTest : public ::testing::Test - Google Test
public class CalculatorTest {

    // add()
    // JUnit 3 - 메소드의 이름이 test로 시작한다면, test 메소드가 됩니다.
    // TEST_F(CalculatorTest, testAdd) {} - Google Test
    // JUnit 4/5 - @Test

    // 단위 테스트의 품질 측정
    // 1. 가독성
    // 2. 유지보수성
    // 3. 신뢰성
    @Test
    public void testAdd() {
        // 아무것도 수행하지 않으면 테스트는 성공합니다.
        // * 작성 중인 테스트는 실패하여야 합니다.
        //  => 강제로 테스트를 실패하는 방법
        // fail("작성 중입니다.");

        Calculator calculator = new Calculator();
        int expected = 24;
        int actual;


        calculator.add(2);
        calculator.add(22);
        actual = calculator.display();

        // 단위 테스트 코드 안에서는 절대 제어 구문이 나오면 안됩니다.
        // (if 문, 반복문, 예외 처리 같은 구문이 발생하면 안된다)
        // => 특정한 조건이 성립되는지 여부를 검증하는 방법 => 단언문(단언 메소드)
        assertEquals("2 + 22", expected, actual);

        // assertEquals를 사용할 때 주의할 점
        // * expected가 먼저오고, actual이 나중에 옵니다.
        /*
        if (calculator.display() != 24) {
            fail("2 + 22");
        }
        */
    }

    // 테스트의 가독성을 높이는 좋은 접근 방식 중 하나는 테스트 이름을 개선하는 것 입니다.
    //  => 테스트의 시나리오가 테스트 메소드 안에 드러나도록 해야 한다.
    //  ex) 테스트대상메소드_시나리오_기대값
    // public void add_2더하기2_결과는4() {

    // BDD(행위 주도 개발)
    //   * 상태 검증 보다는 행위 검증
    //   * 코드의 가독성을 최대한 끌어올린다.

    // 테스트를 구성하는 방법 - '3A'
    //  1. Arrange(준비) - Given: 객체를 생성하고 필요한 경우 적절하게 설정한다.
    //  2. Act(작용)     - When : 객체에 작용을 가한다.
    //  3. Assert(단언)  - Then : 기대하는 바를 단언한다.
    @Test
    public void add_AddingTwoPlusTwo_DisplaysFour() {
        // Test Fixture
        //  개념: xUnit 에서는 SUT를 실행하기 전에 해줘야 하는 사전 작업
        //       (객체 생성, 초기화, 준비 작업)
        //    => 픽스쳐를 설치(setup) 한다.
        // Arrange
        Calculator calculator = new Calculator();
        int expected = 24;
        int actual;

        // Act
        calculator.add(2);
        calculator.add(2);
        actual = calculator.display();

        // Assert
        assertEquals("2 + 2", expected, actual);
    }

}
















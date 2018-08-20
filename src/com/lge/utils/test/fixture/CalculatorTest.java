package com.lge.utils.test.fixture;

import com.lge.utils.Calculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// 픽스쳐 설치 방법 1. Inline fixture setup
//   : 모든 픽스쳐 설치를 테스트 메소드 안에서 처리한다.

// Pros: 테스트의 인과 관계를 쉽게 분석 가능하다.
// Cons: 모든 테스트 메소드 안에서 각각 설치해야 하므로 '테스트 코드 중복'이 발생합니다.

// Test Smells: 테스트의 품질을 떨어뜨리는 요소
//  "테스트 코드 중복"
//  1. 깨지기 쉬운 테스트를 만든다.
//  2. 픽스쳐를 설치하는 단계가 복잡하다면, 테스트를 이해하기 어렵게 만든다.

/*
public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int expected = 24;
        int actual;

        calculator.add(2);
        calculator.add(22);
        actual = calculator.display();

        assertEquals("2 + 22", expected, actual);
    }

    @Test
    public void add_AddingTwoPlusTwo_DisplaysFour() {
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
*/

// 픽스쳐 설치 방법 2. Delegate setup(위임 설치)
//   : 테스트 메소드에서 생성 메소드를 호출해서 테스트 메소드 별로 픽스쳐를 생성한다.
// => 객체 생성에 대한 복잡함을 캡슐화할 수 있고,
//    각 테스트 메소드에 대한 인과 관계도 쉽게 이해할 수 있다.

/*
public class CalculatorTest {
    // 테스트 코드 안에서 발생하는 중복을 없애기 위해서는 Test Utility Method를 구성할 수 있다.
    // => 생성 메소드
    private Calculator createCalculator() {
        return new Calculator(0);
    }

    @Test
    public void testAdd() {
        Calculator calculator = createCalculator();
        int expected = 24;
        int actual;

        calculator.add(2);
        calculator.add(22);
        actual = calculator.display();

        assertEquals("2 + 22", expected, actual);
    }

    @Test
    public void add_AddingTwoPlusTwo_DisplaysFour() {
        // Arrange
        Calculator calculator = createCalculator();
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
*/

// 픽스쳐 설치 방법 3. 암묵적 설치/해체(Implicit set up/tear down)
//  : xUnit Test Framework가 기본적으로 지원하는 기능을 이용해서 픽스쳐를 설치하는 방법.
//  => 여러 테스트 메소드에서 같은 테스트 픽스쳐를 setup() 메소드에서 생성한다.

// 'xUnit Test Framework'이 테스트를 수행하는 방법
// => 'Framework'은 미리 정의된 실행 흐름이 존재한다.

// Pros: 테스트 코드 중복을 제거하고, 불필요한 상호 작용 코드를 모아 유지보수성을 개선할 수 있다.
// Cons: 테스트 메소드를 이해하기 어렵다.

// Test Runner Program - 신선한 픽스쳐 전략
//   : 각각의 테스트 메소드가 독립적으로 수행될 수 있도록 한다.

//  CalculatorTest testcase = new CalculatorTest();
//  testcase.setUp();
//  testcase.testA();
//  testcase.tearDown();

//  CalculatorTest testcase = new CalculatorTest();
//  testcase.setUp();
//  testcase.testB();
//  testcase.tearDown();

// xUnit Test Pattern 에서 테스트 메소드를 구성하는 방법
// => 4단계 테스트 패턴(Four phase test pattern)
// 1단계: 픽스쳐를 설치하거나 실제 결과를 관찰하기 위해 필요한 것을 집어넣는 작업. - setUp()
// 2단계: SUT와 상호 작용한다.
// 3단계: 기대 결과가 나왔는지 단언한다.
// 4단계: 픽스쳐를 해체해서 테스트 시작 상태로 되돌려 놓는다. - tearDown()

public class CalculatorTest {
    private Calculator calculator;

    public CalculatorTest() {
        System.out.println("TestCase()");
    }

    // Fixture를 암묵적으로 설치하기 위해서 제공되는 함수
    @Before
    public void setUp() {
        System.out.println("setUp");
        calculator = new Calculator();
    }

    // 설치된 픽스쳐를 적절하게 해체해서, 테스트 메소드 수행 이전의 상태로 돌려놓는다.
    @After
    public void tearDown() {
        System.out.println("tearDown");
        // 메모리 자원(C/C++) 또는 비 메모리 자원(File, Thread, Process)을 적절하게 해체한다.
    }

    @Test
    public void testAdd() {
        System.out.println("testA");
        int expected = 24;
        int actual;
        calculator.add(2);
        calculator.add(22);
        actual = calculator.display();

        assertEquals("2 + 22", expected, actual);
    }

    @Test
    public void add_AddingTwoPlusTwo_DisplaysFour() {
        System.out.println("testB");
        // Arrange
        int expected = 4;
        int actual;

        // Act
        calculator.add(2);
        calculator.add(2);
        actual = calculator.display();

        // Assert
        assertEquals("2 + 2", expected, actual);
    }
}
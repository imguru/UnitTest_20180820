package ex1;

// * xUnit Test Framework의 기능을 간단하게 살펴보겠습니다.

import org.junit.Test;

import static org.junit.Assert.*;

public class XUnitSamples {


    // foo 함수에 잘못된 문자열을 전달하였을 때, NumberFormatException이 제대로 발생하는지
    // 여부를 검증하고 싶다. => 예외 테스트(JUnit 3)
    @Test
    public void fooTest() {
        String v = "x16";

        try {
            int actual = foo(v);
            fail("예외가 발생하지 않음");
        } catch (NumberFormatException e) {
            assertTrue(true);  // success()
        } catch (Exception e) {
            fail("다른 종류의 예외가 발생하였음");
        }
    }

    // SUT
    public int foo(String x) {
        return Integer.parseInt(x);
        // throw new IllegalStateException();
        // return 42;
    }

    // 예외 테스트를 작성하는 방법_JUnit4
    // => 아래의 코드는 JUnit5 에서는 단언 메소드로 변경되었습니다.
    // Google Test = ASSERT_THROW()
    @Test(expected = NumberFormatException.class)
    public void fooTest_JUnit4() {
        // 자기 설명적인 변수명과 값을 사용하는 것이 좋다. = '가독성'
        String badNumberString = "x16";

        foo(badNumberString);
    }




}

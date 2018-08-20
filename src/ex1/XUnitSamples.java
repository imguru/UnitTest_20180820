package ex1;

// * xUnit Test Framework의 기능을 간단하게 살펴보겠습니다.

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

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

    // 1. 예외 테스트를 작성하는 방법_JUnit4
    // => 아래의 코드는 JUnit5 에서는 단언 메소드로 변경되었습니다.
    // Google Test = ASSERT_THROW()
    @Test(expected = NumberFormatException.class)
    public void fooTest_JUnit4() {
        // 자기 설명적인 변수명과 값을 사용하는 것이 좋다. = '가독성'
        String badNumberString = "x16";

        foo(badNumberString);
    }


    // 2. 비기능 테스트(시간) - 'Google Test'는 지원하지 않습니다.
    //  특정한 기능을 수행하였을 때, 정상 동작하더라도, 반드시 200ms 안에 수행되어야 한다.
    void goo() throws Exception {
        // TimeUnit.SECONDS.sleep(1);
    }

    @Test(timeout = 200) // 200ms
    public void timeoutTest() throws Exception {
        // startTime

        goo();

        // endTime
    }

    // 작성 중이거나 문제가 있는 테스트를 절대 주석 처리하면 안됩니다.
    // => 잊혀진 테스트
    // => 테스트 프레임워크 안에서 임시적으로 테스트를 비활성화할 수 있어야 한다.
    //  : 테스트의 결과에 비활성화되어 있는 테스트가 존재한다고 나와야 한다.

    // 3. 테스트 비활성화
    @Ignore // @Disabled
    @Test
    public void disabledTest() {
        fail("작성 중 입니다.");
    }

    @Test
    public void arrayEqualsTest() {
        String[] name1 = { "Tom", "Bob" };
        String[] name2 = { "Tom", "Bob" };

        // 배열을 비교할 때는 요소를 비교할 수 있는 전용 단언 메소드가 있습니다.
        // assertEquals(name1, name2);
        assertArrayEquals(name1, name2);
    }

    @Test
    public void floatEqualsTest() {
        double a = 0.7;
        double b = 0.1 * 7;

        // 부동 소수점 타입은 오차가 존재하기 때문에,
        // 반드시 오차 범위를 지정하는 전용 메소드를 사용해야 합니다.
        assertEquals(a, b, 0.00001);
    }
}

// Google Test - Windows / Linux
//  : 정적 라이브러리를 사용해서 빌드하는 것이 추천됩니다.

// 1. wget https://github.com/google/googletest/archive/release-1.8.0.tar.gz
// 2. tar xvf release-1.8.0.tar.gz
// 3. mv googletest-release-1.8.0/ googletest
// 4. ubuntu:~/googletest/googletest/scripts$ ./fuse_gtest_files.py gtest
// 5. g++ gtest_all.cc -c
// 6. ar rcv gtest.a gtest_all.o
// 7. g++ calc_test.cpp -I. gtest/gtest.a -lpthread




























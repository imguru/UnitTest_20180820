package ex2;

import org.junit.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

// SetUp과 TearDown()에서 호출되는 픽스쳐 설치와 해체의 과정이 시간이 오래 걸린다.
// => 시간이 오래 걸리면, 테스트 메소드가 늘어날 때마다 전체 테스트 수행 시간이 급격하게 늘어나는 문제가
//    발생합니다.

// 해결 방법: Suite Fixture Set Up / Tear Down Pattern
//   Pros: 느린 테스트의 문제를 쉽게 해결 가능하다.
//   Cons: 신선한 픽스쳐 전략 => 공유 픽스쳐 전략
//         - 테스트 메소드가 더 이상 독립적이지 않다.
//         - 다른 테스트 메소드의 동작이 다른 테스트 메소드에 영향을 주기 쉽다.
//         - 공유 픽스쳐의 상태에 따라서 테스트가 성공해야 함에도 불구하고 실패하거나, 실패해야 함에도 성공하는
//           '변덕스러운 테스트'가 발생할 수 있으므로 주의가 필요하다.
//   * 공유 픽스쳐를 사용하는 테스트 메소드의 개수가 많다면, 변덕스러운 테스트가 발생할 확률이 높으므로
//     테스트 케이스 클래스를 쪼개자.

// Test Suite
//  : Fixture 공유하는 테스트 메소드의 집합

// Slow Test Problem
// => 테스트를 수행하는 시간이 너무 느려서, SUT가 변경되어도 개발자들이 매번 테스트를 수행하지 않는다.
//    테스트를 수행하는 개발자의 생산성을 떨어뜨린다.

// Test Runner
// TestCase.setUpTestCase() - static
// TestCase()
//   setUp()
//   testA()
//   tearDown()
// TestCase()
//   setUp()
//   testB()
//   tearDown()
// TestCase.tearDownTestCase()


public class TerminalTest {
    private static Terminal terminal;

    @BeforeClass
    public static void setUpTestCase() throws Exception {
        System.out.println("setUpTestCase");
        terminal = new Terminal();
        terminal.connect();
    }

    @AfterClass
    public static void tearDownTestCase() throws Exception {
        System.out.println("tearDownTestCase");
        terminal.disconnect();
    }


    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");

    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");

    }


    @Test
    public void loginTest() throws Exception {
        System.out.println("loginTest");
        terminal.login("TEST_USER", "TEST_PASSWORD");

        assertTrue(terminal.isLogin());
    }

    @Test
    public void logoutTest() throws Exception {
        System.out.println("logoutTest");
        terminal.login("TEST_USER", "TEST_PASSWORD");
        terminal.logout();

        // assertFalse(terminal.isLogin());
    }

    @Test
    public void newTest1() throws Exception {

    }
    @Test
    public void newTest2() throws Exception {

    }
    @Test
    public void newTest3() throws Exception {

    }

}


// SUT
class Terminal {
    // 만약 connect와 disconnect가 느리다면?
    public void connect() throws Exception {
        TimeUnit.SECONDS.sleep(2);
    }

    public void disconnect() throws Exception {
        TimeUnit.SECONDS.sleep(1);
    }

    public void login(String userId, String password) {

    }

    public void logout() {

    }

    public boolean isLogin() {
        return true;
    }
}
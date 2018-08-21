package ex5;

// JUnit 4: All in one
// JUnit 5
//   1. JUnit Platform: JVM에서 testing framework을 수행하기 위한 API 정의
//   2. JUnit Jupiter: 새로운 Testing API
//   3. JUnit Vintage: Junit 3 + Junit 4

// JUnit 5 요구 사항
//  JUnit 4: Java 5 이상
//  Junit 5: Java 8 이상

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// 1. Annotation 이름이 좀 더 명확하게 변경되었습니다.

public class JUnit5Sample {
    // @BeforeClass
    @BeforeAll
    public static void setUpTestCase() {
        System.out.println("setUpTestCase");
    }

    // @AfterClass
    @AfterAll
    public static void tearDownTestCase() {
        System.out.println("tearDownTestCase");
    }

    // @Before
    @BeforeEach
    public void setUp() {
        System.out.println("setUp");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("tearDown");
    }

    @Test
    public void testA() {
        System.out.println("  testA");
    }

    @Test
    public void testB() {
        System.out.println("  testB");
    }

    // 2. Assert Method 인자 순서 변경
    // assertEquals("message", expected, value);
    @Test
    public void testC() {
        String arg = null;
        Double expected = null;

        Double actual = StringUtils.convertToDouble(arg);

        // 메세지를 작성하는 위치가 맨 앞에서 뒤로 변경되었습니다.
        assertEquals(expected, actual, "hello");
        assertNull(actual, "hello");
    }

    // 3. assertAll(Google Test: EXPECT_)
    @DisplayName("1990을 실수형으로 변경하였을 때 제대로 동작하는지 여부를 검증한다.")
    @Test
    public void testD() {
        String age = "1990";
        Double expectedAge = Double.valueOf(age);

        Double actual = StringUtils.convertToDouble(age);

        assertAll("Many Assertions", () -> {
            assertNotNull(actual);
            assertEquals(expectedAge, actual);
        });

        StringUtils utils = new StringUtils();

    }

}

class StringUtils {
    void foo() {

    }

    @Nested
    class InnerClass {
        @Test
        public void fooTest() {

        }
    }

    static Double convertToDouble(String str) {
        if (str == null)
            return null;
        return Double.valueOf(str);
    }
}







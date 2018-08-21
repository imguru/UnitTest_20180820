package ex4.testdouble.mock;

// 행위 기반 검증
// 1. 호출 여부 검증
// 2. 호출 횟수 검증
// 3. 호출 인자 검증
// 4. 호출 순서 검증


// 테스트 대역을 이용해야 한다.
//  => 협력 객체을 통해서 '간접 호출'에 대한 부분을 검증.

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

/*
public class MockitoSample {
    // foo를 호출하였을 때, s에 대해서 add("one")과 add("two")가 호출되었는지
    // 여부를 검증하고 싶다.
    @Test
    public void fooTest() {
        SUT sut = new SUT();
        // mock(Class): 동적으로 모의 객체를 생성할 수 있습니다.
        List<String> mock = mock(List.class);

        sut.foo(mock);

        verify(mock).add("one");
        verify(mock).add("two");
    }

    // 호출 횟수 검증
    //  : 몇번 호출되었는지 정확하게 지정해야 한다.

    // 정확한 횟수가 아닌 범위를 통한 횟수도 지정 가능합니다.
    //   times(N)
    //   atLeast(N): 적어도 N번, atMost(N): 최대 N번
    @Test
    public void gooTest() {
        SUT sut = new SUT();
        List<String> mock = mock(List.class);

        sut.goo(mock);

        // verify(mock, times(1)).add("once");
        verify(mock).add("once");

        // verify(mock, times(2)).add("twice");
        // verify(mock, atLeastOnce()).add("twice");
        verify(mock, atMost(1)).add("twice");
    }

    @Test
    public void gooTest_arg() {
        SUT sut = new SUT();
        List<String> mock = mock(List.class);

        sut.goo(mock);

        // "twice" 인자로는 2번 호출되지만,
        // 문자열 인자로는 몇 번 호출되는가?
        // verify(mock, times(2)).add("twice");
        // verify(mock, times(2)).add(any());
        // verify(mock, times(3)).add(any());
        verify(mock, atLeastOnce()).add(any());
    }

    // 호출 순서 판단
    @Test
    public void hooTest() {
        SUT sut = new SUT();
        List<String> mock = mock(List.class);

        sut.hoo(mock);

        // 아래 처럼 사용 할 경우 순서는 상관없이 호출 여부만 판단한다.
        // verify(mock).add("first");
        // verify(mock).add("second");
        // verify(mock).add("third");

        // 순서를 검증하기 위해서는 별도의 객체를 사용해야 합니다.
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).add("first");
        inOrder.verify(mock).add("second");
        inOrder.verify(mock).add("third");
    }
}
*/
public class MockitoSample {
    private SUT sut;

    @Mock
    private List<String> mock;

    @SuppressWarnings("unchecked")  // 1번째 방법
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // 2번째 방법
        //  : 테스트 클래스 내부의 필드 중 '@Mock'으로 지정된 필드를 'Mock'으로 생성해준다.

        sut = new SUT();

        // mock = mock(List.class);
    }

    @After
    public void tearDown() {

    }


    @Test
    public void fooTest() {
        sut.foo(mock);

        verify(mock).add("one");
        verify(mock).add("two");
    }

    @Test
    public void gooTest() {
        sut.goo(mock);

        // verify(mock, times(1)).add("once");
        verify(mock).add("once");

        // verify(mock, times(2)).add("twice");
        // verify(mock, atLeastOnce()).add("twice");
        verify(mock, atMost(1)).add("twice");
    }

    @Test
    public void gooTest_arg() {
        sut.goo(mock);

        verify(mock, atLeastOnce()).add(any());
    }


    @Test
    public void hooTest() {
        sut.hoo(mock);

        // 순서를 검증하기 위해서는 별도의 객체를 사용해야 합니다.
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).add("first");
        inOrder.verify(mock).add("second");
        inOrder.verify(mock).add("third");
    }
}


class SUT {
    public void hoo(List<String> s) {
        s.add("first");
        s.add("second");
        s.add("third");
    }


    public void foo(List<String> s) {
        s.add("one");
        s.add("two");
    }

    public void goo(List<String> s) {
        s.add("once");

        s.add("twice");
        s.add("twice");
    }
}













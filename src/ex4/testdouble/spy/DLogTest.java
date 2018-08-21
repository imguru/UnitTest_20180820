package ex4.testdouble.spy;

// 테스트 대역 용도 3.
//  => Test Spy Pattern
//   : 목격한 일을 기록해두었다가, 나중에 테스트에서 확인할 수 있도록 만들어진 테스트 대역


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

// 테스트 대역 용도 4. SUT의 메소드를 호출하였을 때, 발생하는 부수 효과를 관찰할 수 없어서
//                 테스트 안된 요구사항을 검증하고 싶다.
// => Mock Object Pattern
//   : 상태 기반 검증이 아닌 행위 기반 검증으로 해결할 수 있다.

// 상태 검증(State Verification)
// : 테스트에 검증해야 하는 상태가 있을 때 사용하는 방법

// 행위 검증(Behavior Verification)
// : 올바른 로직 수행의 판단의 근거로 특정한 동작(메소드 호출)을 검증하는 방법
//  1) 호출 여부
//  2) 호출 인자
//  3) 호출 순서
//  4) 호출 횟수
//  현대적인 프로그래밍 설계 방식 - OOP + FP(함수형 프로그래밍)
//  => FP
//     순수 함수 / 불변 객체 => 행위 검증
//  => xUnit Test Framework + Mock Framework
//  => 'Mock'을 동적으로 생성 가능하다.
//    : 별도의 코드를 작성하지 않고도, Mock을 생성할 수 있습니다.

//  => Mock Framework은 협력 객체를 Mock으로 변경해주는 기능
//    C++: Google Mock
//    Java: jMock, EasyMock, Mockito


enum Level {
    INFO, WARN, ERROR
}

interface Target {
    void write(Level level, String message);
}

// FileTarget, NetworkTarget
class DLog {
    private Target[] targets;
    public DLog(Target... targets) {
        this.targets = targets;
    }

    public void write(Level level, String message) {
        for (Target e : targets) {
            e.write(level, message);
        }
    }
}

/*
class SpyTarget implements Target {
    private List<String> logs = new ArrayList<>();

    private String concat(Level level, String message) {
        return level.name() + "@" + message;
    }

    // 검증을 위한 메소드를 제공한다.
    public boolean received(Level level, String message) {
        return logs.contains(concat(level, message));
    }

    @Override
    public void write(Level level, String message) {
        // 목격한 일을 기록한다.
        logs.add(concat(level, message));
    }
}

public class DLogTest {
    @Test
    public void writeTest() {
        SpyTarget spy1 = new SpyTarget();
        SpyTarget spy2 = new SpyTarget();
        DLog log = new DLog(spy1, spy2);
        Level level = Level.ERROR;
        String message = "test message";

        log.write(level, message);

        assertTrue(spy1.received(level, message));
        assertTrue(spy2.received(level, message));
    }
}

// SUT
enum Level {
    INFO, WARN, ERROR
}

interface Target {
    void write(Level level, String message);
}

// FileTarget, NetworkTarget
class DLog {
    private Target[] targets;
    public DLog(Target... targets) {
        this.targets = targets;
    }

    public void write(Level level, String message) {
        for (Target e : targets) {
            e.write(level, message);
        }
    }
}
*/










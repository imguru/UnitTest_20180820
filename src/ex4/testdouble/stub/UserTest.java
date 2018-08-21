package ex4.testdouble.stub;

import org.junit.Test;

import java.io.IOException;

// 제어할 수 없는 협력 객체 : 외부 의존물
//  Stub 목적: 외부 의존물을 원하는 형태로 제어 가능하다.
//   => 특수한 상황을 시뮬레이션 하고 싶다.
//      네트워크, 시간, 배터리 ...

// Test Stub Pattern
// => 다른 컴포넌트로부터의 간접 입력에 의존하는 로직을 독립적으로 검증하고 싶다.
//  : 실제 의존하는 객체를 테스트용 객체로 교체해서, SUT가 테스트하는데 필요한 결과를 보내도록 변경한다.

class BadTCPConnection implements Connection {
    @Override
    public void move(int x, int y) throws IOException {
        // TCP의 연결이 끊겼을 때 IOException 예외가 발생합니다.
        throw new IOException();
    }
}

public class UserTest {
    // 아래 코드의 문제점?
    //  : 네트워크 연결이 성립되지 않았을 때만, 성공하는 '단위 테스트'

    // 단위 테스트는 어떤 환경에서 동작해도 항상 동일한 결과가 반환되어야 한다.

    // 연결이 종료되었을 때, User.move()의 동작에 대해서 IOException이 발생하는지
    // 여부를 검증하고 싶다. - 예외 테스트
    @Test(expected = IOException.class)
    public void moveTest() throws Exception {
        // User user = new User(new TCPConnection());
        BadTCPConnection stub = new BadTCPConnection();
        User user = new User(stub);

        user.move(10, 42);
    }
}


// SUT
interface Connection {
    void move(int x, int y) throws IOException;
}

class TCPConnection implements Connection {

    @Override
    public void move(int x, int y) throws IOException {
        // TCP connection
        // 연결이 끊어지면, IOException이 발생합니다.
    }
}

class User {
    private Connection connection;

    public User(Connection connection) {
        this.connection = connection;
    }

    public void move(int x, int y) throws IOException {
        // 특정 조건이 만족하면
        connection.move(x, y);
    }
}
package ex4.testdouble;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertTrue;

// 테스트 대역
//  => 테스트 전용 하위 클래스 패턴(Test Specific Subclass Pattern)
//    방법: SUT를 상속 받은 클래스에 '테스트에 필요한 상태'나 '동작을 드러내는 메소드'를 호출한다.

// 자식 클래스 is a 부모 클래스
class TestEngine extends Engine {
    private boolean isStarted = false;

    @Override
    public void start() {
        super.start();        //  부모의 기능과 동일한 기능을 수행한다.
        isStarted = true;
    }

    boolean isStarted() {
        return isStarted;
    }
}

class User {
    private String name = "Tom";
}

public class EngineTest {
    @Test
    public void nameTest() throws Exception {
        // Java - Reflection
        // : private 필드의 값을 읽어오는 것도 가능합니다.
        ex4.testdouble.stub.User user = new ex4.testdouble.stub.User();
        Class clazz = user.getClass();

        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        String name = (String)nameField.get(user);

        System.out.println(name);
    }

    // Car 객체에 대해서 go를 호출하면, engine 객체가 start 되었는지를 검증하고 싶다.
    @Test
    public void goTest() {
        // Arrange
        Engine engine = new Engine();
        Car car = new Car(engine);

        // Act
        car.go();

        // Assert?
    }

    @Test
    public void goTest_good() {
        // Arrange
        TestEngine engine = new TestEngine();
        Car car = new Car(engine);

        // Act
        car.go();

        // Assert
        assertTrue(engine.isStarted());
    }
}

// SUT
class Engine {
    public void start() {
        System.out.println("Engine started");
    }
}

class Car {
    private Engine engine;
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void go() {
        // 특정 조건이 만족되면
        engine.start();
    }

}









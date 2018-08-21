package ex4.testdouble.fake;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

// 테스트 대역 용도 2. 아직 만들어지지 않은 컴포넌트에 의존하는 객체를 검증하고 싶다.
//   => Fake Object Pattern
//    : SUT가 의존하는 컴포넌트를 훨씬 가볍게 구현된 것으로 변경한다.

//  용도
//  1) 의존 객체를 사용할 수 없을 때
//  2) 의존 객체가 너무 느려서 사용할 수 없을 때
//    : 의존 객체로 인해서 느린 테스트의 문제가 발생할 때
//  3) 여러가지 문제로 의존 객체를 사용하기 어려울 때 사용할 수 있다.

class FakeDatabase implements Database {
    private Map<String, User> data = new HashMap<>();

    @Override
    public void save(String name, User user) {
        data.put(name, user);
    }

    @Override
    public User load(String name) {
        return data.get(name);
    }
}

public class UserManagerTest {
    @Test
    public void createTest() {
        String name = "Tom";
        int age = 42;
        User expected = new User(name, age);
        UserManager manager = new UserManager(new FakeDatabase());

        manager.create(name, age);

        // 주의할 점
        // assertEquals를 통해 사용자 정의 객체를 비교할 경우
        // 반드시 사용자의 객체에 equals가 재정의되어야 합니다. - Java
        // 반드시 연산자 오버로딩을 통해 재정의 되어야 합니다.   - C++

        assertEquals(expected, manager.find(name));
    }
}


// SUT
interface Database {
    void save(String name, User user);

    User load(String name);
}

// 사용자 객체(User)를 데이터베이스 저장하고, 불러오는 기능 - Helper Class
class UserManager {
    private Database database;

    public UserManager(Database database) {
        this.database = database;
    }

    public void create(String name, int age) {
        if (database.load(name) != null) {
            return;
        }

        database.save(name, new User(name, age));
    }

    public User find(String name) {
        if (name == null) {
            return null;
        }

        return database.load(name);
    }
}

// VO(Value Object) / DTO(Data Transfer Object)
class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Guava(Google Java Library) / Java 7
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    /*
    @Override
    public boolean equals(Object obj) {
        // 1. this 체크
        if (this == obj)
            return true;

        // 2. Type 체크
        if (obj == null || obj.getClass() != User.class) {
            return false;
        }

        // 3. 필드 비교
        User u = (User)obj;
        // return u.name.equals(name) && u.age == age;
        return Objects.equals(u.name, name) && u.age == age;
    }
    */
}
















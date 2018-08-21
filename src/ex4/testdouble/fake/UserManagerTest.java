package ex4.testdouble.fake;

public class UserManagerTest {
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
}
















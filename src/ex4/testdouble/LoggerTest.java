package ex4.testdouble;

// 테스트 대역 목적: 테스트 대상 코드를 격리한다.
//   => 작성하는 테스트의 성공과 실패가 CUT에 의해서 결정되어야 한다.
//   => 결함 국소화: 테스트가 실패할 경우 실패 지점을 바로 알 수 있다.

// 핵심
//  : 테스트 대역을 적용하기 위해서는,
//    제품 코드가 '테스트 가능 설계'가 적용되어 있어야 합니다.
//  => 테스트 가능 설계 = 클린 코드
import org.junit.Test;

import static org.junit.Assert.*;

public class LoggerTest {
    @Test
    public void isValidFilename_NameLoggerThan5Chars_ReturnsTrue() {
        String filename = "good_name.log";
        Logger logger = new Logger();

        boolean actual = logger.isValidFilename(filename);

        assertTrue("다섯글자 이상일 때", actual);
        // assertTrue("다섯글자 이상일 때", logger.isValidFilename(filename));
    }

    @Test
    public void isValidFilename_NameShorterThan5Chars_ReturnsFalse() {
        String filename = "ba@.log";
        Logger logger = new Logger();

        boolean actual = logger.isValidFilename(filename);

        assertFalse("다섯글자 미만일 때", actual);
    }

}

// SUT
/*
class FileSystemManager {
    public boolean isValid(String filename) {
        // NTFS, ext3, HFS
        return false;
    }
}

class Logger {
    // hello.log
    //  : 파일의 확장자를 제외한 이름이 다섯 글자 이상이어야 한다.
    public boolean isValidFilename(String filename) {
        //--- Code Under Test ---
        String name = filename.split("\\.")[0];
        if (name.length() < 5)
            return false;
        //--- Code Under Test ---

        FileSystemManager manager = new FileSystemManager();
        return manager.isValid(filename);
    }
}
*/

// 아래 코드는 테스트 대역을 적용할 수 없습니다.
//  => 틈새(seam) 만들기
//   : 테스트 대역이 적용이 불가능한 제품 코드를 적용 가능하도록 리팩토링 하는 것
//  => Logger <-> FileSystemManager : 강한 결합
//   강한 결합: 특정한 객체를 사용할 때, 구체적인 타입에 의존하는 것
//  => '약한 결합'으로 변경해야 합니다.

//  약한 결합의 조건
//  1) 구체적인 타입이 아니라 인터페이스나 추상 클래스에 의존해야 합니다.
//  2) 직접 생성하는 것이 아니라, 외부에서 생성해서 전달 받아야 합니다.
//    => Dependency Injection(DI)
//  의존성 주입
//    1) 생성자 주입: 협력 객체가 반드시 필요한 경우
//    2) 메소드 주입: 협력 객체가 특정 기능을 사용할 때만 필요한 경우

//  보일러플레이트
//   : 반드시 필요하지만, 반복적으로 발생하는 코드

//  * 가난한 자의 의존성 주입
//  FileSystemManager m1 = new FileSystemManager();
//  NetworkManager m2 = new NetworkManager();
//  Logger logger = new Logger(m1, m2);

// * 제품 코드에서 의존성 주입을 고려한다면, 의존성 주입에 대해서
//   보일러플레이트를 없애주는 프레임워크를 사용하는 것이 좋습니다.
//   = Android/Java - Dagger2(Google)

// 1.
interface IFileSystemManager {
    boolean isValid(String filename);
}

// 2.
class FileSystemManager implements IFileSystemManager {
    @Override
    public boolean isValid(String filename) {
        // NTFS, ext3, HFS
        return false;
    }
}

class Logger {
    private IFileSystemManager manager;

    // 기존의 동작이 변경되지 않도록 해주어야 한다.
    public Logger() {
        this.manager = new FileSystemManager();
    }

    public Logger(IFileSystemManager manager) {
        this.manager = manager;
    }
    // hello.log
    //  : 파일의 확장자를 제외한 이름이 다섯 글자 이상이어야 한다.
    public boolean isValidFilename(String filename) {
        //--- Code Under Test ---
        String name = filename.split("\\.")[0];
        if (name.length() < 5)
            return false;
        //--- Code Under Test ---

        // 3.
        // IFileSystemManager manager = new FileSystemManager();
        return manager.isValid(filename);
    }
}

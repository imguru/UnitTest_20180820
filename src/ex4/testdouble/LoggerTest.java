package ex4.testdouble;

// 테스트 대역 목적: 테스트 대상 코드를 격리한다.
//   => 작성하는 테스트의 성공과 실패가 CUT에 의해서 결정되어야 한다.
//   => 결함 국소화: 테스트가 실패할 경우 실패 지점을 바로 알 수 있다.

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



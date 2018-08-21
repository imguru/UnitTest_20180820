package ex4.testdouble;

// 테스트 대역 목적

public class LoggerTest {
    
}

// SUT
class FileSystemManager {
    public boolean isValid(String filename) {
        // NTFS, ext3, HFS
        return true;
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



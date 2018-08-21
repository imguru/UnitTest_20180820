package ex4.testdouble.mock;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

// 'Stub'도 만들 수 있습니다.
public class MockitoSample2 {
    @Test
    public void stubTest() {
        // Arrange
        MP3 stub = mock(MP3.class);
        when(stub.name()).thenReturn("iPod");

        // System.out.println(stub.name());

        assertEquals("iPod", stub.name());
    }
}

interface MP3 {
    String name();
}

// 1.
interface IFileSystemManager {
    boolean isValid(String filename);
}

// 2.
class FileSystemManager implements IFileSystemManager {
    @Override
    public boolean isValid(String filename) {
        // NTFS, ext3, HFS
        throw new IllegalStateException();
        // return false;
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
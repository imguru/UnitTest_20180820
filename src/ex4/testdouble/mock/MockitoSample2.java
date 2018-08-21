package ex4.testdouble.mock;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

// Test Double - 3가지 / 4가지(xUnit Test Pattern)
// 1. Stub
// 2. Fake
// 3. Spy / Mock - 행위 기반 검증

class BadTCPConnection implements Connection {
    @Override
    public void move(int x, int y) throws IOException {
        // TCP의 연결이 끊겼을 때 IOException 예외가 발생합니다.
        throw new IOException();
    }
}

// 'Stub'도 만들 수 있습니다.

public class MockitoSample2 {
    @Test(expected = IOException.class)
    public void moveTest() throws Exception {

        Connection stub = mock(Connection.class);
        // 예외를 던져야 한다.
        // doThrow(new IOException()).when(stub).move(10, 42);
        // doThrow(new IOException()).when(stub).move(anyInt(), anyInt());
        doThrow(new IOException()).when(stub).move(eq(10), anyInt());
        User user = new User(stub);

        user.move(10, 42);
    }


    @Test
    public void stubTest() {
        // Arrange
        MP4 stub = mock(MP4.class);
        when(stub.name()).thenReturn("iPod");

        // System.out.println(stub.name());

        assertEquals("iPod", stub.name());
    }

    @Test
    public void isValidFilename_NameLoggerThan5Chars_ReturnsTrue() {
        String filename = "good_name.log";
        IFileSystemManager stub = mock(IFileSystemManager.class);
        when(stub.isValid(filename)).thenReturn(true);
        Logger logger = new Logger(stub);

        boolean actual = logger.isValidFilename(filename);

        assertTrue("다섯글자 이상일 때", actual);
    }

    @Test
    public void isValidFilename_NameShorterThan5Chars_ReturnsFalse() {
        String filename = "ba@.log";
        IFileSystemManager stub = mock(IFileSystemManager.class);
        when(stub.isValid(filename)).thenReturn(true);
        Logger logger = new Logger(stub);

        boolean actual = logger.isValidFilename(filename);

        assertFalse("다섯글자 미만일 때", actual);
    }
}

interface MP4 {
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
        // throw new IllegalStateException();
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
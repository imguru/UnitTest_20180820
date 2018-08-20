package ex2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalTest {
    private Terminal terminal;
    @Before
    public void setUp() throws Exception {
        terminal = new Terminal();
        terminal.connect();
    }

    @After
    public void tearDown() throws Exception {
        terminal.disconnect();
    }


    @Test
    public void loginTest() throws Exception {
        terminal.login("TEST_USER", "TEST_PASSWORD");

        assertTrue(terminal.isLogin());
    }

    @Test
    public void logoutTest() throws Exception {
        terminal.login("TEST_USER", "TEST_PASSWORD");
        terminal.logout();

        assertFalse(terminal.isLogin());
    }
}


// SUT
class Terminal {
    public void connect() throws Exception {

    }

    public void disconnect() throws Exception {

    }

    public void login(String userId, String password) {

    }

    public void logout() {

    }

    public boolean isLogin() {
        return true;
    }
}
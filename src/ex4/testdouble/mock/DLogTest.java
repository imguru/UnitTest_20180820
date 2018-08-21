package ex4.testdouble.mock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

// Mockito - 행위 기반 검증 도구(mock, spy)
//  : 일반적으로는 mock과 spy의 차이점이 없습니다.

public class DLogTest {
    @Test
    public void writeTest() {
//        SpyTarget spy1 = new SpyTarget();
//        SpyTarget spy2 = new SpyTarget();
        Target spy1 = spy(Target.class);
        Target spy2 = spy(Target.class);

        DLog log = new DLog(spy1, spy2);
        Level level = Level.ERROR;
        String message = "test message";

        log.write(level, message);

        verify(spy1).write(level, message);
        verify(spy2).write(level, message);
//        assertTrue(spy1.received(level, message));
//        assertTrue(spy2.received(level, message));
    }

    @Test
    public void playTest() {
        MP3 mock = spy(MP3.class);
        SmartPhone smartPhone = new SmartPhone();

        smartPhone.playMusic(mock);

        verify(mock).play();
    }
}

// SUT
// 1. mock, spy를 하든 interface 타입에 대해서는 동일하게 동작합니다.
// 2. mock은 행위를 기록하기만 하지만, spy는 실제 동작이 관찰된다.
// abstract class MP3 {

// 모던 프로그래밍 언어에서는 인터페이스가 기본 구현도 제공할 수 있다.
interface MP3 {
    default void play() {
        System.out.println("Play MP3");
    }
    void stop();

    // 기존의 기능을 통해서 제공할 수 있는 기능 - 기본 구현
    // : default method(defender method)
    default void playOneMinute() {
        play();
        // ...
        stop();
    }
}

class AMP3 implements MP3 {
    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }
}

class SmartPhone {
    public void playMusic(MP3 mp3) {
        mp3.play();
    }
}


enum Level {
    INFO, WARN, ERROR
}

interface Target {
    void write(Level level, String message);
}

// FileTarget, NetworkTarget
class DLog {
    private Target[] targets;

    public DLog(Target... targets) {
        this.targets = targets;
    }

    public void write(Level level, String message) {
        for (Target e : targets) {
            e.write(level, message);
        }
    }
}











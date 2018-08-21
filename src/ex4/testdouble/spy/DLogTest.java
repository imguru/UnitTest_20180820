package ex4.testdouble.spy;

public class DLogTest {
}

// SUT
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










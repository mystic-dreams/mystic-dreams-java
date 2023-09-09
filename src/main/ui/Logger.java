package main.ui;

public class Logger {

    private static LogLevel logLevel = LogLevel.INFO; // Defaults to Info

    private Logger() {
    }

    public static void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    public static void debug(String message) {
        if (logLevel.ordinal() > LogLevel.DEBUG.ordinal()) {
            return;
        }
        log(LogLevel.DEBUG, message);
    }

    public static void info(String message) {
        if (logLevel.ordinal() > LogLevel.INFO.ordinal()) {
            return;
        }
        log(LogLevel.INFO, message);
    }

    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    private static void log(LogLevel level, String message) {
        System.out.println("[" + level + "] " + message);
    }
}

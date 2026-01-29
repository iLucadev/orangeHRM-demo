package ar.org.icaro.infrastructure;

/**
 * Logger genérico para infraestructura de automatización
 */
public class Logger {

    private Logger() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void error(String message, Exception e) {
        System.out.println("[ERROR] " + message + ": " + e.getMessage());
    }

    public static void debug(String message) {
        System.out.println("[DEBUG] " + message);
    }
}

package ar.org.icaro.infrastructure;

/**
 * Logger centralizado para tests
 */
public class TestLogger {

    private static final String SEPARATOR_MAJOR = "==========================================";
    private static final String SEPARATOR_MINOR = "------------------------------------------";
    private static final String PREFIX_SUCCESS = "   ✓ ";
    private static final String PREFIX_ERROR = "   ✗ ";
    private static final String PREFIX_STEP = "   → ";

    private TestLogger() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void logScenarioStart(String scenarioName) {
        System.out.println(SEPARATOR_MAJOR);
        System.out.println("INICIANDO ESCENARIO: " + scenarioName);
        System.out.println(SEPARATOR_MAJOR);
    }

    public static void logScenarioSuccess(String scenarioName) {
        System.out.println(SEPARATOR_MINOR);
        System.out.println(PREFIX_SUCCESS + "ESCENARIO EXITOSO: " + scenarioName);
        System.out.println(SEPARATOR_MAJOR + "\n");
    }

    public static void logScenarioFailure(String scenarioName) {
        System.out.println(SEPARATOR_MINOR);
        System.out.println(PREFIX_ERROR + "ESCENARIO FALLIDO: " + scenarioName);
    }

    public static void logStep(String message) {
        System.out.println(PREFIX_STEP + message);
    }

    public static void logSuccess(String message) {
        System.out.println(PREFIX_SUCCESS + message);
    }

    public static void logError(String message) {
        System.out.println(PREFIX_ERROR + message);
    }

    public static void logError(String message, Exception e) {
        System.out.println(PREFIX_ERROR + message + ": " + e.getMessage());
    }

    public static void logSeparator() {
        System.out.println(SEPARATOR_MINOR);
    }

    public static void logMajorSeparator() {
        System.out.println(SEPARATOR_MAJOR);
    }
}

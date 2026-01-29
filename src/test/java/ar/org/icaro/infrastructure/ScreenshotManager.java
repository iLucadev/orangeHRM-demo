package ar.org.icaro.infrastructure;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Gestión de capturas de pantalla
 */
public class ScreenshotManager {

    private ScreenshotManager() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void captureScreenshot(WebDriver driver, Scenario scenario) {
        if (driver == null) {
            TestLogger.logError("No se puede capturar screenshot: driver es null");
            return;
        }

        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "failure-screenshot");
            TestLogger.logSuccess("Screenshot capturado");
        } catch (Exception e) {
            TestLogger.logError("Error al capturar screenshot", e);
        }
    }
}

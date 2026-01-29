package ar.org.icaro.runner;

import ar.org.icaro.infrastructure.DriverManager;
import ar.org.icaro.infrastructure.ScreenshotManager;
import ar.org.icaro.infrastructure.TestLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

/**
 * Orquestador del ciclo de vida de tests Cucumber
 */
public class Hooks {

    /**
     * ThreadLocal permite que cada thread tenga su propia instancia de WebDriver
     * Requerido para ejecución paralela de escenarios
     */
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Obtiene el WebDriver del thread actual
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    @Before
    public void setUp(Scenario scenario) {
        TestLogger.logScenarioStart(scenario.getName());
        driver.set(DriverManager.createDriver());
    }

    @After
    public void tearDown(Scenario scenario) {
        TestLogger.logSeparator();

        WebDriver currentDriver = driver.get();

        if (scenario.isFailed()) {
            TestLogger.logScenarioFailure(scenario.getName());
            ScreenshotManager.captureScreenshot(currentDriver, scenario);
        } else {
            TestLogger.logScenarioSuccess(scenario.getName());
        }

        DriverManager.quitDriver(currentDriver);
        driver.remove();
    }
}

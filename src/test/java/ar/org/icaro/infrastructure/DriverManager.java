package ar.org.icaro.infrastructure;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Gestión del ciclo de vida de WebDriver
 */
public class DriverManager {

    private DriverManager() {
        throw new UnsupportedOperationException("Utility class");
    }

    static {
        WebDriverManager.chromedriver().setup();
        TestLogger.logSuccess("WebDriverManager configurado");
    }

    public static WebDriver createDriver() {
        TestLogger.logStep("Iniciando ChromeDriver...");

        ChromeOptions options = createChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        TestLogger.logSuccess("Chrome iniciado correctamente");
        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
            TestLogger.logSuccess("Chrome cerrado");
        }
    }

    private static ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-search-engine-choice-screen");
        return options;
    }
}

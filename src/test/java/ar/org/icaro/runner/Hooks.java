package ar.org.icaro.runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Hooks - Manejo del ciclo de vida del WebDriver
 *
 * Clase 13: Integración Cucumber
 * - @Before: Ejecuta antes de cada Scenario
 * - @After: Ejecuta después de cada Scenario
 * - Driver STATIC compartido por todos los Steps
 * - Captura screenshots en failures
 */
public class Hooks {

    // ===============================
    // WEBDRIVER COMPARTIDO
    // ===============================
    /**
     * Driver static compartido por todos los Step Definitions
     * Se inicializa en @Before y se cierra en @After
     */
    public static WebDriver driver;

    // ===============================
    // BEFORE SCENARIO
    // ===============================
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("==========================================");
        System.out.println("INICIANDO ESCENARIO: " + scenario.getName());
        System.out.println("==========================================");

        // Configurar ChromeDriver automáticamente
        WebDriverManager.chromedriver().setup();

        // Opciones de Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-search-engine-choice-screen");
        // Opcional: ejecutar en modo headless
        // options.addArguments("--headless");

        // Inicializar driver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        System.out.println("   ✓ Chrome iniciado correctamente");
    }

    // ===============================
    // AFTER SCENARIO
    // ===============================
    @After
    public void tearDown(Scenario scenario) {
        System.out.println("------------------------------------------");

        // Capturar screenshot si el escenario falló
        if (scenario.isFailed()) {
            System.out.println("   ✗ ESCENARIO FALLIDO: " + scenario.getName());
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "failure-screenshot");
                System.out.println("   ✓ Screenshot capturado");
            } catch (Exception e) {
                System.out.println("   ✗ Error al capturar screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("   ✓ ESCENARIO EXITOSO: " + scenario.getName());
        }

        // Cerrar navegador
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("   ✓ Chrome cerrado");
        }

        System.out.println("==========================================\n");
    }
}

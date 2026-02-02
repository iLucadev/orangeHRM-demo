package ar.org.icaro.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner - Configuración de Cucumber + TestNG
 *
 * Clase 13: Integración Cucumber
 * - Configura ubicación de features y steps
 * - Define plugins de reportes
 * - Extiende AbstractTestNGCucumberTests para ejecutar con TestNG
 *
 * Ejecución paralela:
 * - Configurado en testng.xml con thread-count=${thread.count}
 * - Valor por defecto: 6 threads (definido en pom.xml)
 * - Override: mvn test -Dthread.count=4
 */
@CucumberOptions(
        // ========================================
        // UBICACIÓN DE ARCHIVOS .FEATURE
        // ========================================
        features = "src/test/resources/features",

        // ========================================
        // PAQUETES CON STEP DEFINITIONS Y HOOKS
        // ========================================
        glue = {
                "ar.org.icaro.runner",  // Hooks
                "ar.org.icaro.steps"     // Step Definitions
        },

        // ========================================
        // PLUGINS DE REPORTES
        // ========================================
        plugin = {
                "pretty",                                    // Consola con colores
                "html:target/cucumber-reports.html",        // Reporte HTML
                "json:target/cucumber.json"                 // Reporte JSON (CI/CD)
        },

        // ========================================
        // OPCIONES DE EJECUCIÓN
        // ========================================
        monochrome = true,      // Output legible en consola
        dryRun = false,         // false = ejecutar tests (true = solo validar steps)
        publish = false         // false = no publicar en Cucumber Reports service

        // ========================================
        // TAGS OPCIONALES
        // ========================================
        // Ejemplos:
        // tags = "@login"                    → Solo autenticación
        // tags = "@negative"                 → Solo casos de error
        // tags = "@login and @negative"      → Login fallidos
        // tags = "not @slow"                 → Excluir tests lentos
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Habilita ejecución paralela de escenarios Cucumber
     *
     * DataProvider con parallel=true + testng.xml thread-count
     * permite ejecutar múltiples escenarios simultáneamente.
     *
     * Cantidad de threads configurada en pom.xml (default: 6)
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

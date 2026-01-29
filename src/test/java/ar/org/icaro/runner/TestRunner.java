package ar.org.icaro.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestRunner - Configuración de Cucumber + TestNG
 *
 * Clase 13: Integración Cucumber
 * - Configura ubicación de features y steps
 * - Define plugins de reportes
 * - Extiende AbstractTestNGCucumberTests para ejecutar con TestNG
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
     * DataProvider con parallel=true permite múltiples threads
     */
    @Override
    @org.testng.annotations.DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

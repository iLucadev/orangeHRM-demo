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
        // Descomentar para ejecutar solo escenarios específicos:
        // tags = "@smoke"
        // tags = "@login"
        // tags = "@e2e"
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // Esta clase no necesita código
    // La extensión de AbstractTestNGCucumberTests permite que TestNG ejecute Cucumber
}

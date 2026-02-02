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
 * Ejecución secuencial:
 * - Sin paralelización para máxima estabilidad con OrangeHRM Demo
 * - Tests ejecutados uno a la vez
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
     * DataProvider para ejecución secuencial de escenarios Cucumber
     *
     * Sin parallel=true → ejecuta escenarios uno a la vez
     * Proporciona máxima estabilidad con OrangeHRM Demo
     */
    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

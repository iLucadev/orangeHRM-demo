package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * PIMPage - Page Object para el módulo PIM (Personnel Information Management)
 *
 * Clase 10: Page Object Model básico
 * - Cada Page Object tiene su propio driver y wait
 * - Localizadores privados
 * - Métodos públicos de interacción
 */
public class PIMPage {

    // ===============================
    // ATRIBUTOS
    // ===============================
    private WebDriver driver;
    private WebDriverWait wait;

    // ===============================
    // LOCALIZADORES
    // ===============================
    private By employeeNameInput = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By resultsTable = By.cssSelector(".oxd-table-card");
    private By loadingSpinner = By.cssSelector(".oxd-loading-spinner");

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ===============================
    // MÉTODOS DE INTERACCIÓN
    // ===============================
    public PIMPage searchEmployeeByName(String employeeName) {
        // Esperar que desaparezca el spinner
        waitForLoadingSpinnerToDisappear();

        // Ingresar nombre del empleado
        WebElement nameInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(employeeNameInput)
        );
        nameInput.clear();
        nameInput.sendKeys(employeeName);

        // Esperar para autocomplete (OrangeHRM usa autocomplete)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click en buscar
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        // Esperar que carguen resultados
        waitForLoadingSpinnerToDisappear();

        return this;
    }

    // ===============================
    // VERIFICACIONES
    // ===============================
    public boolean isOnPIMPage() {
        return waitForUrlContains("pim");
    }

    public boolean hasResults() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(resultsTable));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // ===============================
    // MÉTODOS AUXILIARES
    // ===============================
    private boolean waitForUrlContains(String text) {
        try {
            wait.until(ExpectedConditions.urlContains(text));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void waitForLoadingSpinnerToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
        } catch (TimeoutException e) {
            // Si no aparece spinner, continuar
        }
    }
}

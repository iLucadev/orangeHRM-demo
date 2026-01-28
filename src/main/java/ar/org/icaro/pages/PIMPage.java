package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * PIMPage - Page Object para el módulo PIM (Personnel Information Management)
 *
 * Clase 11: Refactorizado para usar BasePage
 * - Extiende de BasePage para heredar driver, wait y métodos comunes
 * - Usa super(driver) para inicializar la clase padre
 * - Usa métodos heredados: type(), click(), isElementVisible(), waitForUrlContains(), waitForElementToDisappear()
 * - Elimina métodos auxiliares duplicados
 */
public class PIMPage extends BasePage {

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
        super(driver);  // Inicializa driver y wait en BasePage
    }

    // ===============================
    // MÉTODOS DE INTERACCIÓN
    // ===============================
    public PIMPage searchEmployeeByName(String employeeName) {
        waitForElementToDisappear(loadingSpinner);  // Método heredado
        type(employeeNameInput, employeeName);      // Método heredado

        // Esperar para autocomplete (OrangeHRM usa autocomplete)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        click(searchButton);                        // Método heredado
        waitForElementToDisappear(loadingSpinner);  // Método heredado

        return this;
    }

    // ===============================
    // VERIFICACIONES
    // ===============================
    public boolean isOnPIMPage() {
        return waitForUrlContains("pim");  // Método heredado
    }

    public boolean hasResults() {
        return isElementVisible(resultsTable);  // Método heredado
    }
}

package ar.org.icaro.pages;

import ar.org.icaro.strategies.AutocompleteHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
    private WebElement employeeNameInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(css = ".oxd-table-card")
    private WebElement resultsTable;

    @FindBy(css = ".oxd-toast-content--info, .orangehrm-horizontal-padding span")
    private WebElement noRecordsMessage;

    /**
     * loadingSpinner permanece como By (no @FindBy)
     *
     * Razón técnica: waitForElementToDisappear() requiere By locator
     * para esperar invisibilidad. @FindBy es para elementos con los que
     * interactuamos (click, type), no para esperar que desaparezcan.
     */
    private By loadingSpinner = By.cssSelector(".oxd-loading-spinner");

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public PIMPage(WebDriver driver) {
        super(driver);
    }

    // ===============================
    // MÉTODOS DE INTERACCIÓN
    // ===============================
    public PIMPage searchEmployeeByName(String employeeName) {
        waitForElementToDisappear(loadingSpinner);
        type(employeeNameInput, employeeName);
        AutocompleteHandler.waitForAutocomplete();
        click(searchButton);
        waitForElementToDisappear(loadingSpinner);
        return this;
    }

    // ===============================
    // VERIFICACIONES
    // ===============================
    public boolean isOnPIMPage() {
        return waitForUrlContains("pim");
    }

    public boolean hasResults() {
        return isElementVisible(resultsTable);
    }

    public boolean hasNoResults() {
        return isElementVisible(noRecordsMessage);
    }

    public String getNoResultsMessage() {
        return getText(noRecordsMessage);
    }

    public int getResultCount() {
        waitForElementToDisappear(loadingSpinner);
        try {
            return driver.findElements(By.cssSelector(".oxd-table-card")).size();
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public boolean hasMultipleResults() {
        return getResultCount() > 1;
    }

    /**
     * Limpia el campo de búsqueda y sincroniza el estado
     *
     * Fix: Agrega sincronización después de clear() para evitar
     * race condition en búsquedas consecutivas con servidor lento
     */
    public PIMPage clearSearch() {
        // Limpiar campo
        employeeNameInput.clear();

        // Esperar que el spinner desaparezca (si OrangeHRM procesó el clear)
        waitForElementToDisappear(loadingSpinner);

        // CRÍTICO: Esperar que la tabla de resultados DESAPAREZCA
        // Si había resultados previos, deben limpiarse antes de la siguiente búsqueda
        // Esto evita estado residual que confunde a OrangeHRM
        try {
            wait.until(driver -> {
                try {
                    return !resultsTable.isDisplayed();
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    return true;  // Tabla ya no existe = OK
                }
            });
        } catch (org.openqa.selenium.TimeoutException e) {
            // Si después de 15s la tabla sigue visible, continuar igual
            // Esto puede pasar si OrangeHRM no limpia los resultados
        }

        // Pequeño delay para estabilización del DOM
        AutocompleteHandler.waitForAutocomplete(500);

        return this;
    }

    public PIMPage clickSearchWithoutInput() {
        waitForElementToDisappear(loadingSpinner);
        click(searchButton);
        waitForElementToDisappear(loadingSpinner);
        return this;
    }
}

package ar.org.icaro.pages;

import ar.org.icaro.strategies.AutocompleteHandler;
import org.openqa.selenium.By;
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
}

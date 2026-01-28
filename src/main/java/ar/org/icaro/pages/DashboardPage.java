package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * DashboardPage - Page Object para el Dashboard de OrangeHRM
 *
 * Clase 11: Refactorizado para usar BasePage
 * - Extiende de BasePage para heredar driver, wait y métodos comunes
 * - Usa super(driver) para inicializar la clase padre
 * - Usa métodos heredados: click(), getText(), waitForUrlContains(), waitForElementToDisappear()
 * - Elimina métodos auxiliares duplicados
 */
public class DashboardPage extends BasePage {

    // ===============================
    // LOCALIZADORES
    // ===============================
    private By dashboardHeader = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
    private By pimMenuItem = By.xpath("//span[text()='PIM']");
    private By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private By logoutLink = By.xpath("//a[text()='Logout']");
    private By loadingSpinner = By.cssSelector(".oxd-loading-spinner");

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public DashboardPage(WebDriver driver) {
        super(driver);  // Inicializa driver y wait en BasePage
    }

    // ===============================
    // MÉTODOS DE NAVEGACIÓN
    // ===============================
    public PIMPage goToPIM() {
        waitForElementToDisappear(loadingSpinner);  // Método heredado
        waitForUrlContains("dashboard");             // Método heredado
        click(pimMenuItem);                          // Método heredado
        return new PIMPage(driver);
    }

    public LoginPage logout() {
        waitForElementToDisappear(loadingSpinner);  // Método heredado
        click(userDropdown);                        // Método heredado
        click(logoutLink);                          // Método heredado
        return new LoginPage(driver);
    }

    // ===============================
    // VERIFICACIONES
    // ===============================
    public boolean isOnDashboard() {
        return waitForUrlContains("dashboard");  // Método heredado
    }

    public String getHeaderText() {
        return getText(dashboardHeader);  // Método heredado
    }
}

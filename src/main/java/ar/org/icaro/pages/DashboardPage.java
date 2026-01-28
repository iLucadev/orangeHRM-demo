package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(css = "h6.oxd-topbar-header-breadcrumb-module")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenuItem;

    @FindBy(css = ".oxd-userdropdown-tab")
    private WebElement userDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutLink;

    private By loadingSpinner = By.cssSelector(".oxd-loading-spinner");

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // ===============================
    // MÉTODOS DE NAVEGACIÓN
    // ===============================
    public PIMPage goToPIM() {
        waitForElementToDisappear(loadingSpinner);
        waitForUrlContains("dashboard");
        click(pimMenuItem);
        return new PIMPage(driver);
    }

    public LoginPage logout() {
        waitForElementToDisappear(loadingSpinner);
        click(userDropdown);
        click(logoutLink);
        return new LoginPage(driver);
    }

    // ===============================
    // VERIFICACIONES
    // ===============================
    public boolean isOnDashboard() {
        return waitForUrlContains("dashboard");
    }

    public String getHeaderText() {
        return getText(dashboardHeader);
    }
}

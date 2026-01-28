package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * DashboardPage - Page Object para el Dashboard de OrangeHRM
 *
 * Clase 10: Page Object Model básico
 * - Cada Page Object tiene su propio driver y wait
 * - Localizadores privados
 * - Métodos públicos de interacción
 */
public class DashboardPage {

    // ===============================
    // ATRIBUTOS
    // ===============================
    private WebDriver driver;
    private WebDriverWait wait;

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
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ===============================
    // MÉTODOS DE NAVEGACIÓN
    // ===============================
    public PIMPage goToPIM() {
        // Esperar que desaparezca el spinner de carga
        waitForLoadingSpinnerToDisappear();

        // Esperar URL del dashboard
        waitForUrlContains("dashboard");

        // Click en menu PIM
        wait.until(ExpectedConditions.elementToBeClickable(pimMenuItem)).click();

        return new PIMPage(driver);
    }

    public LoginPage logout() {
        // Esperar que desaparezca el spinner
        waitForLoadingSpinnerToDisappear();

        // Click en dropdown de usuario
        wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).click();

        // Click en logout
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();

        return new LoginPage(driver);
    }

    // ===============================
    // VERIFICACIONES
    // ===============================
    public boolean isOnDashboard() {
        return waitForUrlContains("dashboard");
    }

    public String getHeaderText() {
        WebElement header = wait.until(
            ExpectedConditions.visibilityOfElementLocated(dashboardHeader)
        );
        return header.getText();
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

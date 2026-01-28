package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * LoginPage - Page Object para la página de login de OrangeHRM
 *
 * Clase 10: Page Object Model básico
 * - Cada Page Object tiene su propio driver y wait
 * - Localizadores privados
 * - Métodos públicos de interacción
 */
public class LoginPage {

    // ===============================
    // ATRIBUTOS
    // ===============================
    private WebDriver driver;
    private WebDriverWait wait;

    // ===============================
    // LOCALIZADORES
    // ===============================
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By loginPanel = By.cssSelector(".orangehrm-login-slot");

    private static final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/";

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ===============================
    // MÉTODOS DE NAVEGACIÓN
    // ===============================
    public LoginPage goTo() {
        driver.get(LOGIN_URL);
        return this;
    }

    // ===============================
    // MÉTODOS DE INTERACCIÓN
    // ===============================
    public LoginPage enterUserName(String username) {
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(usernameField)
        );
        element.clear();
        element.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(passwordField)
        );
        element.clear();
        element.sendKeys(password);
        return this;
    }

    public DashboardPage clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new DashboardPage(driver);
    }

    // ===============================
    // MÉTODO DE ALTO NIVEL
    // ===============================
    public DashboardPage loginAs(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        return clickLogin();
    }

    // ===============================
    // VERIFICACIONES
    // ===============================
    public boolean isOnLoginPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPanel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

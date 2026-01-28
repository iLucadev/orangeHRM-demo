package ar.org.icaro.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage - Page Object para la página de login de OrangeHRM
 *
 * Clase 11: Refactorizado para usar BasePage
 * - Extiende de BasePage para heredar driver, wait y métodos comunes
 * - Usa super(driver) para inicializar la clase padre
 * - Usa métodos heredados: type(), click(), isElementVisible()
 * - Elimina código duplicado
 */
public class LoginPage extends BasePage {

    // ===============================
    // LOCALIZADORES
    // ===============================
    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = ".orangehrm-login-slot")
    private WebElement loginPanel;

    private static final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/";

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public LoginPage(WebDriver driver) {
        super(driver);
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
        type(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public DashboardPage clickLogin() {
        click(loginButton);
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
        return isElementVisible(loginPanel);
    }
}

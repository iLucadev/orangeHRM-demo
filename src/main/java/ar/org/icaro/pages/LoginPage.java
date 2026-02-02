package ar.org.icaro.pages;

import ar.org.icaro.validators.ErrorMessageValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(css = ".oxd-alert-content-text")
    private WebElement errorMessage;

    @FindBy(css = ".oxd-input-field-error-message")
    private List<WebElement> requiredFieldMessages;

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

    /**
     * Verifica si se muestra mensaje de error de credenciales inválidas
     */
    public boolean isErrorDisplayed() {
        return isElementVisible(errorMessage);
    }

    /**
     * Obtiene el texto del mensaje de error
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Verifica si se muestra mensaje de campo requerido
     *
     * Fix: Agrega wait explícito para que los mensajes se rendericen
     * antes de validar (evita race condition con servidor lento)
     */
    public boolean isRequiredFieldMessageDisplayed() {
        try {
            // Esperar que al menos 1 mensaje de error esté visible
            wait.until(driver -> requiredFieldMessages.size() >= 1);
            return ErrorMessageValidator.hasErrorMessage(requiredFieldMessages);
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    /**
     * Verifica si se muestran múltiples mensajes de campos requeridos
     *
     * Fix: Agrega wait explícito para que los mensajes se rendericen
     * antes de validar (evita race condition con servidor lento)
     */
    public boolean areRequiredFieldMessagesDisplayed() {
        try {
            // Esperar que al menos 2 mensajes de error estén visibles
            wait.until(driver -> requiredFieldMessages.size() >= 2);
            return ErrorMessageValidator.hasExpectedErrorCount(requiredFieldMessages, 2);
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
}

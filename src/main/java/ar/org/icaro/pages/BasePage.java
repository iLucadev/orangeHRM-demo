package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage - Clase base abstracta para todos los Page Objects
 *
 * Clase 11: Patrón BasePage con herencia
 * - Extrae código común de todos los Page Objects
 * - Atributos PROTECTED para acceso desde clases hijas
 * - Métodos comunes reutilizables
 * - Evita duplicación de código
 * - Soporte para Page Factory con @FindBy annotations
 */
public abstract class BasePage {

    // ===============================
    // ATRIBUTOS COMPARTIDOS
    // ===============================
    protected WebDriver driver;
    protected WebDriverWait wait;

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ===============================
    // MÉTODOS COMUNES DE INTERACCIÓN
    // ===============================

    /**
     * Click en elemento esperando que sea clickeable (By locator)
     */
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Click en elemento esperando que sea clickeable (WebElement)
     */
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * Escribir texto en campo esperando visibilidad (By locator)
     */
    protected void type(By locator, String text) {
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        );
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Escribir texto en campo esperando visibilidad (WebElement)
     */
    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Obtener texto de elemento (By locator)
     */
    protected String getText(By locator) {
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        );
        return element.getText();
    }

    /**
     * Obtener texto de elemento (WebElement)
     */
    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    /**
     * Verificar si elemento es visible (By locator)
     */
    protected boolean isElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Verificar si elemento es visible (WebElement)
     */
    protected boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Esperar que URL contenga texto específico
     */
    protected boolean waitForUrlContains(String text) {
        try {
            wait.until(ExpectedConditions.urlContains(text));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Esperar que elemento desaparezca (invisibilidad)
     * Útil para loading spinners de OrangeHRM
     */
    protected boolean waitForElementToDisappear(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}

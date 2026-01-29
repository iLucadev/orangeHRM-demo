package ar.org.icaro.strategies;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Estrategias de espera explícita
 */
public class WaitStrategy {

    /**
     * OrangeHRM requiere 15 segundos (la aplicación es lenta)
     */
    public static final int WAIT_TIMEOUT_SECONDS = 15;

    private final WebDriverWait wait;

    public WaitStrategy(WebDriverWait wait) {
        this.wait = wait;
    }

    public static WaitStrategy withTimeout(WebDriver driver, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return new WaitStrategy(wait);
    }

    public static WaitStrategy withDefaultTimeout(WebDriver driver) {
        return withTimeout(driver, WAIT_TIMEOUT_SECONDS);
    }

    public boolean waitForUrlContains(String text) {
        try {
            wait.until(ExpectedConditions.urlContains(text));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Espera que un elemento desaparezca (invisibilidad)
     * Crítico para loading spinners en OrangeHRM
     */
    public boolean waitForElementToDisappear(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}

package ar.org.icaro.validators;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Validación de mensajes de error en formularios
 */
public class ErrorMessageValidator {

    private ErrorMessageValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean hasErrorMessage(List<WebElement> errorMessages) {
        return hasExpectedErrorCount(errorMessages, 1);
    }

    public static boolean hasExpectedErrorCount(List<WebElement> errorMessages, int expectedCount) {
        try {
            return errorMessages.size() >= expectedCount &&
                   allErrorMessagesVisible(errorMessages);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean allErrorMessagesVisible(List<WebElement> errorMessages) {
        try {
            return errorMessages.stream()
                    .allMatch(WebElement::isDisplayed);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

package ar.org.icaro.strategies;

import ar.org.icaro.infrastructure.Logger;

/**
 * Manejo de delays para campos autocomplete
 */
public class AutocompleteHandler {

    /**
     * OrangeHRM autocomplete requiere ~1 segundo para cargar opciones
     */
    public static final int AUTOCOMPLETE_DELAY_MS = 1000;

    private AutocompleteHandler() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void waitForAutocomplete() {
        waitForAutocomplete(AUTOCOMPLETE_DELAY_MS);
    }

    public static void waitForAutocomplete(int delayMs) {
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Logger.error("Interrupción durante espera de autocomplete", e);
            Thread.currentThread().interrupt();
        }
    }
}

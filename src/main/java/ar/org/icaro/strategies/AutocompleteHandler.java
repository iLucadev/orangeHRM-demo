package ar.org.icaro.strategies;

import ar.org.icaro.infrastructure.Logger;

/**
 * Manejo de delays para campos autocomplete
 */
public class AutocompleteHandler {

    /**
     * OrangeHRM autocomplete requiere ~1.5 segundos para cargar opciones
     *
     * Fix: Aumentado de 1000ms a 1500ms para servidor lento (OrangeHRM Demo)
     *
     * Nota técnica: Thread.sleep es necesario porque:
     * - OrangeHRM no expone eventos de autocomplete completado
     * - El dropdown de autocomplete aparece/desaparece dinámicamente
     * - No hay locator confiable para esperar con WebDriverWait
     */
    public static final int AUTOCOMPLETE_DELAY_MS = 1500;

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

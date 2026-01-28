package ar.org.icaro.steps;

import ar.org.icaro.pages.DashboardPage;
import ar.org.icaro.pages.LoginPage;
import ar.org.icaro.pages.PIMPage;
import ar.org.icaro.runner.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;

/**
 * FlujoPrincipalSteps - Step Definitions para flujo_completo.feature
 *
 * Clase 13: Integración Cucumber
 * - Implementa pasos Gherkin en español usando anotaciones en inglés
 * - Usa Hooks.driver (static) para acceder al WebDriver
 * - Utiliza Page Objects para interactuar con la aplicación
 * - Contiene aserciones con TestNG
 */
public class FlujoPrincipalSteps {

    // ===============================
    // PAGE OBJECTS
    // ===============================
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    // ===============================
    // GIVEN STEPS (Precondiciones)
    // ===============================

    @Given("que estoy en la página de login de OrangeHRM")
    public void iAmOnOrangeHRMLoginPage() {
        System.out.println("   → Navegando a la página de login");
        loginPage = new LoginPage(Hooks.driver);
        loginPage.goTo();
        Assert.assertTrue(
                loginPage.isOnLoginPage(),
                "Debería estar en la página de login"
        );
    }

    // ===============================
    // WHEN STEPS (Acciones)
    // ===============================

    @When("ingreso el usuario {string}")
    public void iEnterUsername(String username) {
        System.out.println("   → Ingresando usuario: " + username);
        loginPage.enterUserName(username);
    }

    @When("ingreso la contraseña {string}")
    public void iEnterPassword(String password) {
        System.out.println("   → Ingresando contraseña");
        loginPage.enterPassword(password);
    }

    @When("hago click en el botón Login")
    public void iClickLoginButton() {
        System.out.println("   → Haciendo click en Login");
        dashboardPage = loginPage.clickLogin();
    }

    @When("navego al módulo PIM")
    public void iNavigateToPIMModule() {
        System.out.println("   → Navegando al módulo PIM");
        pimPage = dashboardPage.goToPIM();
    }

    @When("busco al empleado {string}")
    public void iSearchForEmployee(String employeeName) {
        System.out.println("   → Buscando empleado: " + employeeName);
        pimPage.searchEmployeeByName(employeeName);
    }

    @When("cierro sesión")
    public void iLogout() {
        System.out.println("   → Cerrando sesión");
        loginPage = dashboardPage.logout();
    }

    // ===============================
    // THEN STEPS (Verificaciones)
    // ===============================

    @Then("debería estar en el Dashboard")
    public void iShouldBeOnDashboard() {
        System.out.println("   → Verificando que estoy en Dashboard");
        Assert.assertTrue(
                dashboardPage.isOnDashboard(),
                "Debería estar en la página Dashboard después del login"
        );
    }

    @Then("debería ver el título {string}")
    public void iShouldSeeTitle(String expectedTitle) {
        System.out.println("   → Verificando título: " + expectedTitle);
        String actualTitle = dashboardPage.getHeaderText();
        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "El título del Dashboard debería ser '" + expectedTitle + "'"
        );
    }

    @Then("debería estar en la página PIM")
    public void iShouldBeOnPIMPage() {
        System.out.println("   → Verificando que estoy en PIM");
        Assert.assertTrue(
                pimPage.isOnPIMPage(),
                "Debería estar en la página PIM"
        );
    }

    @Then("deberían mostrarse resultados de búsqueda")
    public void searchResultsShouldBeDisplayed() {
        System.out.println("   → Verificando resultados de búsqueda");
        Assert.assertTrue(
                pimPage.hasResults(),
                "Deberían mostrarse resultados de búsqueda para el empleado"
        );
    }

    @Then("debería estar de vuelta en la página de login")
    public void iShouldBeBackOnLoginPage() {
        System.out.println("   → Verificando que estoy de vuelta en login");
        Assert.assertTrue(
                loginPage.isOnLoginPage(),
                "Debería estar de vuelta en la página de login después del logout"
        );
    }

    @Then("debería ver un mensaje de error")
    public void iShouldSeeErrorMessage() {
        System.out.println("   → Verificando mensaje de error");
        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Debería mostrarse mensaje de error de credenciales inválidas"
        );
    }

    @Then("debería permanecer en la página de login")
    public void iShouldRemainOnLoginPage() {
        System.out.println("   → Verificando que permanezco en login");
        Assert.assertTrue(
                loginPage.isOnLoginPage(),
                "Debería permanecer en la página de login después de error"
        );
    }

    @Then("debería ver un mensaje de campo requerido")
    public void iShouldSeeRequiredFieldMessage() {
        System.out.println("   → Verificando mensaje de campo requerido");
        Assert.assertTrue(
                loginPage.isRequiredFieldMessageDisplayed(),
                "Debería mostrarse mensaje de campo requerido"
        );
    }

    @Then("debería ver mensajes de campos requeridos")
    public void iShouldSeeRequiredFieldsMessages() {
        System.out.println("   → Verificando mensajes de campos requeridos");
        Assert.assertTrue(
                loginPage.areRequiredFieldMessagesDisplayed(),
                "Deberían mostrarse mensajes de campos requeridos"
        );
    }

    // ===============================
    // NOTA SOBRE AND STEPS
    // ===============================
    // Cucumber automáticamente trata @When y @Then
    // como implementaciones válidas para "Y" (And) en Gherkin español
}

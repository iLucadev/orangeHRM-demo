package ar.org.icaro.steps;

import ar.org.icaro.pages.DashboardPage;
import ar.org.icaro.pages.LoginPage;
import ar.org.icaro.pages.PIMPage;
import ar.org.icaro.runner.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;

/**
 * Step Definitions para escenarios de autenticación y gestión de empleados
 */
public class MainFlowSteps {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @Given("que estoy en la página de login de OrangeHRM")
    public void iAmOnOrangeHRMLoginPage() {
        loginPage = new LoginPage(Hooks.getDriver());
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
        loginPage.enterUserName(username);
    }

    @When("ingreso la contraseña {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("hago click en el botón Login")
    public void iClickLoginButton() {
        dashboardPage = loginPage.clickLogin();
    }

    @When("navego al módulo PIM")
    public void iNavigateToPIMModule() {
        pimPage = dashboardPage.goToPIM();
    }

    @When("busco al empleado {string}")
    public void iSearchForEmployee(String employeeName) {
        pimPage.searchEmployeeByName(employeeName);
    }

    @When("cierro sesión")
    public void iLogout() {
        loginPage = dashboardPage.logout();
    }

    @Then("debería estar en el Dashboard")
    public void iShouldBeOnDashboard() {
        Assert.assertTrue(
                dashboardPage.isOnDashboard(),
                "Debería estar en la página Dashboard después del login"
        );
    }

    @Then("debería ver el título {string}")
    public void iShouldSeeTitle(String expectedTitle) {
        String actualTitle = dashboardPage.getHeaderText();
        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "El título del Dashboard debería ser '" + expectedTitle + "'"
        );
    }

    @Then("debería estar en la página PIM")
    public void iShouldBeOnPIMPage() {
        Assert.assertTrue(
                pimPage.isOnPIMPage(),
                "Debería estar en la página PIM"
        );
    }

    @Then("deberían mostrarse resultados de búsqueda")
    public void searchResultsShouldBeDisplayed() {
        Assert.assertTrue(
                pimPage.hasResults(),
                "Deberían mostrarse resultados de búsqueda para el empleado"
        );
    }

    @Then("debería estar de vuelta en la página de login")
    public void iShouldBeBackOnLoginPage() {
        Assert.assertTrue(
                loginPage.isOnLoginPage(),
                "Debería estar de vuelta en la página de login después del logout"
        );
    }

    @Then("debería ver un mensaje de error")
    public void iShouldSeeErrorMessage() {
        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Debería mostrarse mensaje de error de credenciales inválidas"
        );
    }

    @Then("debería permanecer en la página de login")
    public void iShouldRemainOnLoginPage() {
        Assert.assertTrue(
                loginPage.isOnLoginPage(),
                "Debería permanecer en la página de login después de error"
        );
    }

    @Then("debería ver un mensaje de campo requerido")
    public void iShouldSeeRequiredFieldMessage() {
        Assert.assertTrue(
                loginPage.isRequiredFieldMessageDisplayed(),
                "Debería mostrarse mensaje de campo requerido"
        );
    }

    @Then("debería ver mensajes de campos requeridos")
    public void iShouldSeeRequiredFieldsMessages() {
        Assert.assertTrue(
                loginPage.areRequiredFieldMessagesDisplayed(),
                "Deberían mostrarse mensajes de campos requeridos"
        );
    }
}

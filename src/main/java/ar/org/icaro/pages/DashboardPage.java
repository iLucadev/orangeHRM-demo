package ar.org.icaro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DashboardPage - Page Object para el Dashboard de OrangeHRM
 *
 * Clase 11: Refactorizado para usar BasePage
 * - Extiende de BasePage para heredar driver, wait y métodos comunes
 * - Usa super(driver) para inicializar la clase padre
 * - Usa métodos heredados: click(), getText(), waitForUrlContains(), waitForElementToDisappear()
 * - Elimina métodos auxiliares duplicados
 */
public class DashboardPage extends BasePage {

    // ===============================
    // LOCALIZADORES
    // ===============================
    @FindBy(css = "h6.oxd-topbar-header-breadcrumb-module")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenuItem;

    @FindBy(css = ".oxd-userdropdown-tab")
    private WebElement userDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutLink;

    @FindBy(css = ".orangehrm-dashboard-widget")
    private List<WebElement> dashboardWidgets;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-dashboard-widget') and descendant::*[text()='Time at Work']]")
    private WebElement timeAtWorkWidget;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-dashboard-widget') and descendant::*[text()='My Actions']]")
    private WebElement myActionsWidget;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-dashboard-widget') and descendant::*[text()='Quick Launch']]")
    private WebElement quickLaunchWidget;

    @FindBy(css = ".oxd-main-menu")
    private WebElement sideMenu;

    /**
     * loadingSpinner permanece como By (no @FindBy)
     *
     * Razón técnica: waitForElementToDisappear() requiere By locator
     * para esperar invisibilidad. @FindBy es para elementos con los que
     * interactuamos (click, type), no para esperar que desaparezcan.
     */
    private By loadingSpinner = By.cssSelector(".oxd-loading-spinner");

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // ===============================
    // MÉTODOS DE NAVEGACIÓN
    // ===============================
    public PIMPage goToPIM() {
        waitForElementToDisappear(loadingSpinner);
        waitForUrlContains("dashboard");
        click(pimMenuItem);
        return new PIMPage(driver);
    }

    public LoginPage logout() {
        waitForElementToDisappear(loadingSpinner);
        click(userDropdown);
        click(logoutLink);
        return new LoginPage(driver);
    }

    // ===============================
    // VERIFICACIONES
    // ===============================
    public boolean isOnDashboard() {
        return waitForUrlContains("dashboard");
    }

    public String getHeaderText() {
        return getText(dashboardHeader);
    }

    public boolean isTimeAtWorkWidgetVisible() {
        return isElementVisible(timeAtWorkWidget);
    }

    public boolean isMyActionsWidgetVisible() {
        return isElementVisible(myActionsWidget);
    }

    public boolean isQuickLaunchWidgetVisible() {
        return isElementVisible(quickLaunchWidget);
    }

    public int getVisibleWidgetCount() {
        waitForElementToDisappear(loadingSpinner);
        return driver.findElements(By.cssSelector(".orangehrm-dashboard-widget")).size();
    }

    public boolean isSideMenuVisible() {
        return isElementVisible(sideMenu);
    }

    public List<String> getSideMenuItems() {
        waitForElementToDisappear(loadingSpinner);
        List<WebElement> menuItems = driver.findElements(By.cssSelector(".oxd-main-menu-item-wrapper"));
        return menuItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean hasWelcomeMessage() {
        return isElementVisible(userDropdown);
    }
}

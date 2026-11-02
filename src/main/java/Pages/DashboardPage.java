package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.SelUtils;

public class DashboardPage extends BasePage {
    private SelUtils sel;

    private final By header = By.cssSelector(".oxd-topbar-header-breadcrumb h6");
    private final By menuItems = By.cssSelector(".oxd-main-menu-item--name");
    private final By menuSearch = By.cssSelector("input[placeholder='Search']");
    private final By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private final By logoutLink = By.xpath("//a[text()='Logout']");

    private By menuItem(String name){
        return By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='" + name + "']");
    }

    public DashboardPage(WebDriver driver){
        super(driver);
        this.sel= new SelUtils(driver);
    }
    public String getHeaderText() {
        return sel.getText(header);
    }
    public boolean isLoaded() {
        return sel.wiatForURL("/dashboard");
    }

    public boolean isMenuItemPresent(String name){
        sel.waitForVisible(menuSearch);
        return !driver.findElements(menuItem(name)).isEmpty();
    }
    public void searchMenu(String text){
        sel.waitForVisible(menuItems);

        int countBefore = driver.findElements(menuItems).size();

        driver.findElement(menuSearch).sendKeys(text);
        // wait for the RESULT of typing: fewer items than before
        wait.until(d -> d.findElements(menuItems).size() < countBefore);
    }
    public LoginPage logout() {
        sel.click(userDropdown);
        sel.click(logoutLink);
        return new LoginPage(driver);
    }
}

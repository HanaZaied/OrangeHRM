package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SelUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage{
    private SelUtils sel;


    private final By userNameLoc = By.name("username");
    private final By passwordLoc = By.name("password");
    private final By loginButtonLoc = By.cssSelector("button[type=\"submit\"]");
    private final By errorMessageLoc = By.cssSelector(".oxd-alert-content-text");
    private final By reqMessageLoc = By.cssSelector(".oxd-input-field-error-message");

    public LoginPage(WebDriver driver){
        super(driver);
        this.sel = new SelUtils(driver);
    }

    public void enterUserName(String userName){
        sel.type(userNameLoc,userName);
    }
    public void enterPassword(String password){
        sel.type(passwordLoc,password);
    }
    public void clickLoginButton(){
        sel.click(loginButtonLoc);
    }
    public void login(String userName,String password){
        enterUserName(userName);
        enterPassword(password);
        clickLoginButton();
    }
    public String getErrorMessage(){
        return sel.getText(errorMessageLoc);
    }
    public List<String> getReqMessage(){
       return sel.getTexts(reqMessageLoc);
    }
    public boolean isLoaded(){
        return sel.wiatForURL("/auth/login");
//        return wait.until(ExpectedConditions.urlContains("/auth/login"));
    }
    public boolean isRedirectedToDashBoard(){
        return sel.wiatForURL("/dashboard");
//        return wait.until(ExpectedConditions.urlContains("/dashboard"));
    }

    public DashboardPage loginToDashBoard(String username, String password){
        login(username,password);
        return new DashboardPage(driver);
    }
}

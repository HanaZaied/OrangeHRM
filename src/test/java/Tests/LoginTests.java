package Tests;

import Base.BaseTests;
import Pages.DashboardPage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class LoginTests extends BaseTests {
    private LoginPage loginPage;

    @BeforeMethod
    public void creatLoginPage(){
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "invalid-logins")
    public Object[][] invalidLogins (){
        return new Object[][]{
                {"Admin","wrong123"},
                {"Hana", "admin123"},
                {"wrongUser", "wrong123"}
        };
    }

    @Test
    public void testValidLogin(){
        loginPage.login("Admin ","admin123");
        assertTrue(loginPage.isRedirectedToDashBoard(),
                "User was not redirected to the dashboard after a valid login");
    }
    @Test(dataProvider = "invalid-logins")
    public void testInvalidLogin(String userName,String password){
        loginPage.login(userName,password);
        assertEquals(loginPage.getErrorMessage(),"Invalid credentials",
                "Wrong error message for user" + userName + "'");
        assertTrue(loginPage.isLoaded(),"User should stay on the login page");
    }

    @Test
    public void testEmptyFields(){
        loginPage.clickLoginButton();
        SoftAssert softAssert = new SoftAssert();
        List<String> messages = loginPage.getReqMessage();

        softAssert.assertEquals(messages.size(),2,
                "Number of 'Required' messages");
        for (String message : messages) {
            softAssert.assertEquals(message, "Required", "Text of a validation message");
        }
        softAssert.assertTrue(loginPage.isLoaded(), "User should stay on the login page");

        softAssert.assertAll();
    }
    @Test
    public void testInvalidLogin2(){
        DashboardPage dashboardPage = loginPage.loginToDashBoard("Admin","admin123");

        assertTrue(dashboardPage.isLoaded(),"User should stay on the login page");
    }

}

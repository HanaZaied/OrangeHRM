package Tests;

import Base.BaseTests;
import Pages.DashboardPage;
import Pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DashBoardTests extends BaseTests {
    private DashboardPage dashboard;

    @BeforeMethod
    public void loginAndOpenDashBoard(){
        LoginPage loginPage = new LoginPage(driver);
        dashboard = loginPage.loginToDashBoard("Admin","admin123");
    }

    @Test
    public void dashBoardIsLoaded(){
        assertEquals(dashboard.getHeaderText(),"Dashboard",
                "Wrong page header");
    }
    @Test
    public void searchShowsMatchingItems() {
        dashboard.searchMenu("Leave");

        assertTrue(dashboard.isMenuItemPresent("Leave"),
                "'Leave' should stay in the menu when we search for it");
        assertFalse(dashboard.isMenuItemPresent("Admin"),
                "'Admin' should be hidden when we search for 'Leave'");
    }
    @Test
    public void searchWithNoMatchShowsNoItems() {
        assertTrue(dashboard.isMenuItemPresent("Leave"),
                "'Leave' should be in the menu before searching");

        dashboard.searchMenu("xyz123");

        assertFalse(dashboard.isMenuItemPresent("Leave"),
                "No menu item should be shown when nothing matches the search");
    }
    @Test
    public void logoutReturnsToLoginPage() {
        LoginPage loginPage = dashboard.logout();

        assertTrue(loginPage.isLoaded(), "Login page did not appear after logout");
    }
}

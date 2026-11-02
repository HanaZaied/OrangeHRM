package utils;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SelUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SelUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement waitForVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public Boolean wiatForURL(String url){
        return wait.until(ExpectedConditions.urlContains(url));
    }
    public void click(By locator){
        waitForVisible(locator).click();
    }
    public void type(By locator,String text){
        WebElement field = waitForVisible(locator);
        field.clear();
        field.sendKeys(text);
    }
    public String getText(By locator){
        return waitForVisible(locator).getText();
    }
    public List<String> getTexts(By locator){
        waitForVisible(locator);
        List<String> text = new ArrayList<>();
        for(WebElement message : driver.findElements(locator)){
            text.add(message.getText());
        }
        return text;
    }
}

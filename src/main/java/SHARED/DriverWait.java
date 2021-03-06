package SHARED;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

public class DriverWait {
    public static void waitForPageLoaded(WebDriver driver) throws InterruptedException,Exception {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };

            Thread.sleep(1000);
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, 30);
            wait.until(expectation);

    }

    public static void implicitWaitPeriod(WebDriver driver, TimeUnit unit, int time){
        driver.manage().timeouts().implicitlyWait(time, unit);
    }

    public static void explicitWaitPeriod(WebDriver driver, int time, By findElement){
        WebDriverWait wait = new WebDriverWait(driver,time);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(findElement));
    }
}

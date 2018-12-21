package SHARED;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class DriverWait {
    public static void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, 30);
            wait.until(expectation);

        } catch (Throwable error) {
            System.out.println("!!! Chyba stránka nenačtena \n"+error);
            System.exit(0);
        }


    }
}

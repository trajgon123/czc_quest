package SHARED;

import org.openqa.selenium.WebDriver;

public class FinishTest {
    public static void endTest(WebDriver driver){
        driver.close();
        driver.quit();
    }

}

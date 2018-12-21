package SHARED;

import org.openqa.selenium.WebDriver;

public class FinishTest {
    public static void endTest(WebDriver driver){
        System.out.println("Ukončuji driver");
        driver.close();
        driver.quit();
        System.exit(0);
    }

}

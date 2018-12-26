package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class cart {


    public static String ContinuInOrderButtonText ="Pokračovat v objednávce";


    static WebElement continueInOrder(WebDriver driver){
        return driver.findElement(By.id("hp-tabpanel1"));
    }
}

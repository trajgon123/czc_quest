package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class home_page {


    public static WebElement onlyTodayProduct(WebDriver driver){
        return driver.findElement(By.id("hp-tabpanel1"));
    }



}

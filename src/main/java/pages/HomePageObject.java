package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObject {

    WebDriver driver = null;
    By onlyTodayProduct = By.id("hp-tabpanel1");

    public HomePageObject(WebDriver _driver){
        this.driver = _driver;

    }

    public WebElement OnlyTodayProduct(){
        return driver.findElement(onlyTodayProduct);
    }

    public void clickOnlyTodayProduct(){
        driver.findElement(onlyTodayProduct).click();
    }

}

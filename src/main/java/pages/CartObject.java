package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartObject {


    WebDriver driver = null;
    By continueInOrderButton = By.cssSelector("button[class='btn btn-purchase']");

    public CartObject(WebDriver _driver){this.driver = _driver;}

    public WebElement continueInOrderButton(){
        return driver.findElement(continueInOrderButton);
    }

}

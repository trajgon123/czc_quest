package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartObject {


    WebDriver driver = null;
    By continueInOrderButton = By.cssSelector("button[class='btn btn-purchase']");
    By orderItemsBox = By.id("basketContainer");
    final String pageURL = "https://www.czc.cz/kosik";


    public CartObject(WebDriver _driver){
        this.driver = _driver;
    }

    public WebElement continueInOrderButton(){
        return driver.findElement(continueInOrderButton);
    }

    public WebElement orderItemsBox(){
        return driver.findElement(orderItemsBox);
    }

    public String getPageURL(){
        return pageURL;
    }



}

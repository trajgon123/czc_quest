package cz.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObject {


    WebDriver driver;
    By header_logo = By.id("ctl00_Header_logoControl");
    final String pageURL = "https://www.synottip.cz/";


    public HomePageObject(WebDriver _driver){
        this.driver = _driver;
    }

    public WebElement getHeader_logo(){
        return driver.findElement(header_logo);
    }

    public String getPageUrl(){
        return pageURL;
    }

}



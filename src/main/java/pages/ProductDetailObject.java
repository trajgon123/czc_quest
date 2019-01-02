package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailObject {

    By addToCart = By.cssSelector("span[class='btn-inner ico-basket'");
    private static WebDriver driver = null;
    final String pageExampleURL="https://www.czc.cz/a/230288/produkt";

    public ProductDetailObject(WebDriver _driver){
        this.driver = _driver;
    }

    public WebElement addToCartButton(){
        return driver.findElement(addToCart);
    }

    public void clickAddToCartButton(){
        driver.findElement(addToCart).click();
    }

    public String getPageExampleURL(){
        return pageExampleURL;
    }


}

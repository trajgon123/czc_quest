package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class product_detail {

    public static String addToCartButtonText="Přidat do košíku";

    public static WebElement addToCart(WebDriver driver){
        return driver.findElement(By.cssSelector("span[class='btn-inner ico-basket'"));
    }

}

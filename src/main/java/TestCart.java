
import SHARED.ChromeWebDriver;
import SHARED.FinishTest;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.util.Properties;


public class TestCart {
    private static String base_url;
    
        public static void main(String[] args) {

            //načti properties
            Properties prop = SHARED.LoadProperties.loadPropertiesFromFile();
           base_url = prop.getProperty("base_url");

            System.out.println("** Spouštím test TestCart**");
            WebDriver driver = ChromeWebDriver.WebDriverInit();

           System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp()+"Jdu na base_url="+base_url);
           driver.get(prop.getProperty("base_url"));
           SHARED.DriverWait.waitForPageLoaded(driver);
           addRandomItemToCart(driver);


            System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "Přecházím do košíku");
            driver.get(base_url+"/kosik");
            SHARED.DriverWait.waitForPageLoaded(driver);
            if(driver.findElements(By.cssSelector("button[class='btn btn-purchase']")).size() != 0
                    && driver.findElements( By.cssSelector(".op-content.clearfix") ).size() != 0){
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp()+"Produkt je v košíku");
            }else {
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "!!! CHYBA Produkt není v košíku !!!");
            }

          FinishTest.endTest(driver);

        }

        public static void addRandomItemToCart(WebDriver driver){
            System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + " Na hlavní stránce hledám produkt 'pouze dnes'");
            if(driver.findElements( By.id("hp-tabpanel1") ).size() != 0){
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "Přecházím na detail produktu");
                driver.findElement(By.id("hp-tabpanel1")).click();
                SHARED.DriverWait.waitForPageLoaded(driver);
            }
            if( !driver.getCurrentUrl().contains("produkt")){
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "Produkt 'pouze dnes' na hlavní stránce není, přecházím na stránku="+base_url+"/a/230286/produkt");
                driver.get(base_url+"/a/230286/produkt");
                SHARED.DriverWait.waitForPageLoaded(driver);
            }
            System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "Klikám na tlačítko přidat do košíku");
            driver.findElement(By.cssSelector("span[class='btn-inner ico-basket']")).click();


        }
    }



import SHARED.ChromeWebDriver;
import SHARED.FinishTest;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.util.Properties;
import pages.*;


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
            System.out.println(driver.getPageSource());
            if(driver.getPageSource().contains(cart.ContinuInOrderButtonText)){
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp()+"Produkt je v košíku");
            }else {
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "!!! CHYBA Produkt není v košíku !!!");
            }

          FinishTest.endTest(driver);

        }

        public static void addRandomItemToCart(WebDriver driver){
            System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + " Na hlavní stránce hledám produkt 'pouze dnes'");
            if(pages.home_page.onlyTodayProduct(driver).isDisplayed()){
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "Přecházím na detail produktu");
                pages.home_page.onlyTodayProduct(driver).click();
                SHARED.DriverWait.waitForPageLoaded(driver);
            }
            if( !driver.getCurrentUrl().contains("produkt")){
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "Produkt 'pouze dnes' na hlavní stránce není, přecházím na stránku="+base_url+"/a/230286/produkt");
                driver.get(base_url+"/a/230286/produkt");
                SHARED.DriverWait.waitForPageLoaded(driver);
            }
            if(driver.getPageSource().contains(product_detail.addToCartButtonText)){
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "Tlačítko přidat do košíku je aktivni");

            }else{
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "Tlačítko přidat do košíku není aktivni (produkt je možná vyprodán)");
                System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "Přecházím na jiný produkt, stránka="+base_url+"/a/230286/produkt");
                driver.get(base_url+"/a/230286/produkt");
                SHARED.DriverWait.waitForPageLoaded(driver);

            }


            System.out.println(SHARED.ActualDateAndTime.DateAndTimeStamp() + "Klikám na tlačítko přidat do košíku");
            pages.product_detail.addToCart(driver).click();





        }
    }


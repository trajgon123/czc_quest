package tests;

import SHARED.ChromeWebDriver;
import SHARED.DriverWait;
import SHARED.FinishTest;
import okio.Timeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;


public class TestUsingPOM {
    private static String base_url;
    private static WebDriver driver;
    private static Properties prop;
    private static Logger logger = null;


    @BeforeTest
    public void prepareTest() {

        //načti properties
        Properties prop = SHARED.LoadProperties.loadPropertiesFromFile();
        base_url = prop.getProperty("base_url");

        //nastartuj logger
        logger = LogManager.getLogger(this.getClass().getName()+".class");
        logger.trace("** Spouštím test "+this.getClass().getName()+" **");
        driver = ChromeWebDriver.WebDriverInit();
        //nastav implicitní Wait
        DriverWait.implicitWaitPeriod(driver,TimeUnit.SECONDS,10);

    }

    @Test
    public void executeTest(){
        logger.trace("Přecházím na stránku-"+base_url);
        driver.get(base_url);
        SHARED.DriverWait.waitForPageLoaded(driver);

        HomePageObject homePageObject=new HomePageObject(driver);
        logger.trace("Na hlavní stránce hledám produkt 'pouze dnes'");
        try{
            homePageObject.OnlyTodayProduct().isDisplayed();
            logger.trace("Přecházím na detail produktu");
            homePageObject.clickOnlyTodayProduct();
        }catch(NoSuchElementException e){
            logger.trace("Produkt 'pouze dnes' na hlavní stránce není, přecházím na stránku="+base_url+"/a/230286/produkt");
            driver.get(base_url+"/a/230286/produkt");
        }catch(Exception e){
            logger.error("!!! Neznámá chyba !!!"+e.toString());
        }
        SHARED.DriverWait.waitForPageLoaded(driver);

        ProductDetailObject productDetailObject = new ProductDetailObject(driver);
        try{
            productDetailObject.addToCartButton().isDisplayed();
            logger.trace("Klikám na tlačítko přidat do košíku");
            productDetailObject.clickAddToCartButton();
        }catch(NoSuchElementException e){
            logger.trace("Tlačítko přidat do košíku nenalezeno, produkt je pravděpodobně vyprodán.");
            logger.trace("Přecházím na stránku="+base_url+"/a/230286/produkt");
            driver.get(base_url+"/a/230286/produkt");
            logger.trace("Klikám na tlačítko přidat do košíku");
            productDetailObject.clickAddToCartButton();
        }catch(Exception e){
            logger.error("!!! Neznámá chyba !!!"+e.toString());
        }

       SHARED.DriverWait.waitForPageLoaded(driver);


        logger.trace("Přecházím do košíku");
        driver.get(base_url+"/kosik");
        SHARED.DriverWait.waitForPageLoaded(driver);
        CartObject cartObject = new CartObject(driver);

        try{
            cartObject.continueInOrderButton().isDisplayed();
            logger.trace("Produkt je v košíku");
        }catch(NoSuchElementException e){
            logger.error("!!! Produkt není v košíku !!!"+ e.toString());
        }catch(Exception e){
            logger.error("!!! Neznámá chyba !!!"+e.toString());
        }



    }

    @AfterTest
    public void tearDown(){
        logger.trace("Ukončuji test");
        FinishTest.endTest(driver);
    }
}



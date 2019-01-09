package tests;

import SHARED.ChromeWebDriver;
import SHARED.DriverWait;
import SHARED.FinishTest;
import okio.Timeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.fail;


public class TestOne {
    private static String home_url;
    private static WebDriver driver;
    private static Properties prop;
    private static Logger logger = null;
    private HomePageObject homePageObject;
    private ProductDetailObject productDetailObject;
    private CartObject cartObject;
    ITestResult result;


    @BeforeTest
    public void prepareTest() {
        try{
        //načti properties
        Properties prop = SHARED.LoadProperties.loadPropertiesFromFile();
        home_url = prop.getProperty("home_url");

        //nastartuj logger
        logger = LogManager.getLogger(this.getClass().getName()+".class");
        logger.trace("** Spouštím test "+this.getClass().getName()+" **");
        driver = ChromeWebDriver.WebDriverInit();

        //nastav implicitní Wait
        DriverWait.implicitWaitPeriod(driver,TimeUnit.SECONDS,10);

        //inicializace PAGE objektů
        homePageObject=new HomePageObject(driver);
        productDetailObject = new ProductDetailObject(driver);
        cartObject = new CartObject(driver);

        }catch (FileNotFoundException e){
           logger.error("!!! Soubor nenalezen (FileNotFoundException) !!! "+e.toString());
           fail();
        }catch (IOException e) {
            logger.error("!!! Chyba při práci se souborem (IOException)!!! "+e.toString());
            fail();
        }catch (Exception e) {
            logger.error("!!! Neznámá chyba !!! " + e.toString());
            fail();
        }
    }

        @org.testng.annotations.Test
       public void executeTest(){
        //přejdi na detail produktu
        try{
            logger.trace("Přecházím na stránku -"+homePageObject.getPageURL());
            driver.get(homePageObject.getPageURL());
            SHARED.DriverWait.waitForPageLoaded(driver);
            logger.trace("Na hlavní stránce hledám produkt 'pouze dnes'");
            homePageObject.OnlyTodayProduct().isDisplayed();
            logger.trace("Přecházím na detail produktu");
            homePageObject.clickOnlyTodayProduct();
        }catch(NoSuchElementException e) {
            logger.trace("Produkt 'pouze dnes' na hlavní stránce není, přecházím na stránku=" + productDetailObject.getPageExampleURL());
            driver.get(productDetailObject.getPageExampleURL());
        }catch (InterruptedException e) {
            logger.error("!!! Vyskytla se InterruptedException chyba !!!"+e.toString());
            fail();
        }catch(Exception e){
            logger.error("!!! Neznámá chyba !!!"+e.toString());
            fail();
        }

        //klikni na tlačítko přidat do košíku
        try{
            logger.trace("Klikám na tlačítko přidat do košíku");
            productDetailObject.clickAddToCartButton();
        }catch(NoSuchElementException e){
            logger.error("!!! Tlačítko přidat do košíku nenalezeno !!!" + e.toString());
            fail();
        }catch(Exception e){
            logger.error("!!! Neznámá chyba !!!"+e.toString());
            fail();
        }

        //ověř produkt v košíku
        try{
            logger.trace("Přecházím do košíku");
            driver.get(cartObject.getPageURL());
            SHARED.DriverWait.waitForPageLoaded(driver);
            cartObject.continueInOrderButton().isDisplayed();

            if(cartObject.continueInOrderButton().isDisplayed()&&cartObject.orderItemsBox().isDisplayed()){
                logger.trace("Produkt je v košíku");
            }else{
                logger.error("!!! Produkt není v košíku !!!");
                fail();
            }

        }catch(NoSuchElementException e){
            logger.error("!!! Produkt není v košíku !!!"+ e.toString());
            fail();
        }catch(Exception e){
            logger.error("!!! Neznámá chyba !!!"+e.toString());
            fail();
        }

    }

    @AfterTest
    public void tearDown(){
        logger.trace("Ukončuji test");
        FinishTest.endTest(driver);
        result = Reporter.getCurrentTestResult();
        logger.trace(result.toString());
    }


}



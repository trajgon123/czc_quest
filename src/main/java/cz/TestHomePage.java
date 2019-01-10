package cz;

import SHARED.ChromeWebDriver;
import SHARED.DriverWait;
import SHARED.FinishTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CartObject;
import pages.HomePageObject;
import pages.ProductDetailObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class TestHomePage {


    private static WebDriver driver;
    private static Logger logger = null;
    private cz.pom.HomePageObject homePageObject;
    ITestResult result;

    @BeforeTest
    public void prepareTest() {
        try{

            //nastartuj logger
            logger = LogManager.getLogger(this.getClass().getName()+".class");
            logger.trace("** Spouštím TestHomePage "+this.getClass().getName()+" **");
            driver = ChromeWebDriver.WebDriverInit();

            //nastav implicitní Wait
            DriverWait.implicitWaitPeriod(driver, TimeUnit.SECONDS,10);

            //inicializace PAGE objektů
            homePageObject=new cz.pom.HomePageObject(driver);

        }catch (Exception e) {
            logger.error("!!! Neznámá chyba !!! " + e.toString());
            fail();
        }


    }
    @Test
    public void executeTest(){
        logger.trace("*** Starting test ***");
        logger.trace("Jdu na stránku=" + homePageObject.getPageUrl());
        logger.trace("Jdu na stránku=" + homePageObject.getPageUrl());
        logger.trace("Jdu na stránku=" + homePageObject.getPageUrl());
        driver.get(homePageObject.getPageUrl());
        if(homePageObject.getHeader_logo().getAttribute("tittle").contains("SynotTIP")){
            logger.trace("Logo má správný tittle");
        }else{
            logger.error("!!! LOGO NEMÁ SPRÁVNÝ TITTLE!!!");
            fail();
        }

    }
    @AfterTest
    public void tearDown(){
        logger.trace("Ukončuji Test");
        FinishTest.endTest(driver);
        result = Reporter.getCurrentTestResult();
        logger.trace(result.toString());
    }
}

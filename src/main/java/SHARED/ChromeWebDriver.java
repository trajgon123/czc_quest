package SHARED;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ChromeWebDriver {


    public static WebDriver WebDriverInit() {

        String pathtodriver = System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe";
        //TestOne if chromedriver is found
        File f = new File(pathtodriver);
        if(!f.exists()) {
            System.out.println("ChromeDriver nenalezen !!! "+System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
            System.exit(0);
        }

        System.setProperty("webdriver.chrome.driver", pathtodriver);


        ChromeOptions options = new ChromeOptions();
        options.addArguments("chrome.switches","--ignore-gpu-blacklist");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver();

        // wait for browser to loadup
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        // maximalize browser window
        driver.manage().window().maximize();

        return driver;
    }


}

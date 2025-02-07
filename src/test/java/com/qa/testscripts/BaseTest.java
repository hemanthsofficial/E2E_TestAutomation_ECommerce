package com.qa.testscripts;

import com.qa.pages.*;
import com.qa.utility.JSONDataReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    WebDriver bot;
    CustomerLoginPages customerLoginPages;
    CustomerAddToCartPages customerAddToCartPages;
    CustomerCartViewPages customerCartViewPages;
    CustomerPlaceOrderPages customerPlaceOrderPages;
    CustomerRegisterPages customerRegisterPages;
    JSONDataReader jsonDataReader;

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        Properties driverConfig = new Properties();
        FileInputStream driverConfigFile = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/qa/testdata/DriverConfig.properties");
        driverConfig.load(driverConfigFile);
//        String driverBinary = driverConfig.getProperty("driverBinary");
        String browser = driverConfig.getProperty("browser");
        String url = driverConfig.getProperty("url");

        if (browser.contentEquals("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "D:\\TEST\\driverbinaries\\chromedriver\\chromedriver.exe");
            bot = new ChromeDriver();
        } else if (browser.contentEquals("edge")) {
            System.setProperty("webdriver.edge.driver",
                    "D:\\TEST\\driverbinaries\\edgedriver\\msedgedriver.exe");
            bot = new EdgeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            bot = new FirefoxDriver();
        }
        bot.navigate().to(url);
        bot.manage().window().maximize();
        bot.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        customerLoginPages = new CustomerLoginPages(bot);
        customerAddToCartPages = new CustomerAddToCartPages(bot);
        customerCartViewPages = new CustomerCartViewPages(bot);
        customerPlaceOrderPages = new CustomerPlaceOrderPages(bot);
        customerRegisterPages = new CustomerRegisterPages(bot);
        jsonDataReader = new JSONDataReader();
    }

    public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
        return jsonDataReader.getJsonDataToMap(jsonFilePath);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (bot != null) {
            bot.quit();
        }
    }
}